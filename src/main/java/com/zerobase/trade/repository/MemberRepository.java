package com.zerobase.trade.repository;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


}
