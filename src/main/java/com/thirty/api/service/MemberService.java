package com.thirty.api.service;

import com.thirty.api.domain.Member;
import com.thirty.api.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ByeongChan on 2018. 1. 16..
 */

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Member save(String uniqueKey){ return memberRepository.save(Member.build(uniqueKey, false)); }

    public Member findByUniqueKey(String uniqueKey){ return memberRepository.findByUniqueKey(uniqueKey); }

    public Member findByMemberId(Long memberId){ return memberRepository.findOne(memberId); }
}
