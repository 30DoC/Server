package com.thirty.api.service;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.dto.StatusType;
import com.thirty.api.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 16..
 */

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Long save(String uniqueKey){
        Member member = Member.build(uniqueKey, StatusType.NONE);

        List<Quiz> quizList = new ArrayList<>();

        // 사용자 질문 10개 리스트 생성
        // 질문 등록 전 이므로 null값으로 저장
        for (int i = 0; i < 10; i++) {
            Quiz quiz = Quiz.build(null, false);
            quizList.add(quiz);
        }

        member.setQuizList(quizList);

        memberRepository.save(member);

        return member.getMemberId();
    }

    public Member findByUniqueKey(String uniqueKey){ return memberRepository.findByUniqueKey(uniqueKey); }

    @Cacheable(value="findIdCache", key="#memberId")
    public Member findByMemberId(Long memberId){ return memberRepository.findOne(memberId); }
}
