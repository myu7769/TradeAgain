package com.zerobase.trade.repository;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {


}
