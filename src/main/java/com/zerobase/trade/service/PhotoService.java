package com.zerobase.trade.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.util.ArrayList;
import java.util.List;

import com.zerobase.trade.domain.entity.Photo;
import com.zerobase.trade.domain.entity.PhotoOrder;
import com.zerobase.trade.domain.photo.PhotoRequestForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.exception.ErrorCode;
import com.zerobase.trade.repository.PhotoOrderRepository;
import com.zerobase.trade.repository.PhotoRepository;
import com.zerobase.trade.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private String S3Bucket = "spring-photo-bucket"; // Bucket 이름

    private final AmazonS3Client amazonS3Client;
    private final PhotoRepository photoRepository;
    private final PhotoOrderRepository photoOrderRepository;

    private final ProductRepository productRepository;


    public List<String> upload(MultipartFile[] multipartFileList ,PhotoRequestForm form) throws Exception {
        List<String> imagePathList = new ArrayList<>();
        Long[] orders = form.getOrder();

        for (int i = 0; i < multipartFileList.length; i++) {
            MultipartFile multipartFile = multipartFileList[i];
            String originalName = multipartFile.getOriginalFilename(); // file name
            long size = multipartFile.getSize(); // file size
            Long currentOrder = orders[i];

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(multipartFile.getContentType());
            objectMetaData.setContentLength(size);

            // Upload to S3
            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); // Get the accessible URL

            Photo photo = photoRepository.save(Photo.builder()
                            .url(imagePath)
                            .name(originalName)
                            .build());

            PhotoOrder photoOrder;

            if(form.getPhotoContext().isChatRoom()){
                photoOrder = PhotoOrder.builder()
                            .photo(photo)
                            .order(currentOrder)
//                            .chatRoom()
                            .build();
            }else{
                photoOrder = PhotoOrder.builder()
                            .photo(photo)
                            .order(currentOrder)
                            .product(productRepository.findById(form.getContextId()).orElseThrow(()->new CustomException(ErrorCode.PRODUCT_NOT_FOUND)))
                            .build();
            }

            photoOrderRepository.save(photoOrder);
            imagePathList.add(imagePath);
        }


        // TODO: 2023-08-08  order 확인 필요, db에 저장

        return imagePathList;
    }

}
