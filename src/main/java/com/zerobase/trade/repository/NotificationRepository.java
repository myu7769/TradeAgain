package com.zerobase.trade.repository;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
