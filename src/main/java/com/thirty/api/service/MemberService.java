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

    public void save(Member member){ memberRepository.save(member); }

    public Member findByUniqueKey(String uniqueKey){ return memberRepository.findByUniqueKey(uniqueKey); }
}
