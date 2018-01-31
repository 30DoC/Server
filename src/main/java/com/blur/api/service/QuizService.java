package com.blur.api.service;

import com.blur.api.domain.Member;
import com.blur.api.domain.Quiz;
import com.blur.api.persistence.MemberRepository;
import com.blur.api.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 19..
 */

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    MemberRepository memberRepository;

    public void save(Quiz quiz){ quizRepository.save(quiz); }

    public List<Quiz> randomSampling(){

        Member member = memberRepository.randomSampling();

        if(member == null){
            return null;
        }

        List<Quiz> quizList = member.getQuizList();

        return quizList;
    }
}
