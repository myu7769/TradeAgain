package com.zerobase.trade.repository;


import com.zerobase.trade.domain.entity.Notification;
import com.zerobase.trade.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
