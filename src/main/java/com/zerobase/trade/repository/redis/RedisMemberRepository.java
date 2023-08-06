package com.zerobase.trade.repository.redis;

import com.zerobase.trade.domain.member.MemberDto;
import org.springframework.data.repository.CrudRepository;


public interface RedisMemberRepository extends CrudRepository<MemberDto, Long> {
}
