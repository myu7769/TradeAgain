package com.zerobase.trade.repository;


import com.zerobase.trade.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

      @Override
      Page<Product> findAll(Pageable pageable);
}
