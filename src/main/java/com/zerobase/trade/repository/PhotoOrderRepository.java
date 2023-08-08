package com.zerobase.trade.repository;


import com.zerobase.trade.domain.entity.PhotoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoOrderRepository extends JpaRepository<PhotoOrder, Long> {

}
