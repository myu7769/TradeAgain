package com.zerobase.trade.repository.redis;

import com.zerobase.trade.domain.member.MemberDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RedisMemberRepository extends CrudRepository<MemberDTO, Long> {
}
