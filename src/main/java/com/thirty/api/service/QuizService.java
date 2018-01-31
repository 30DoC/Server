package com.thirty.api.service;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.dto.QuizRequest;
import com.thirty.api.dto.QuizResponse;
import com.thirty.api.persistence.MemberRepository;
import com.thirty.api.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public void saveQuiz(Long memberId, List<QuizRequest> questions){
        Member member = memberRepository.findOne(memberId);

        List<Quiz> quizList = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            Quiz quiz = Quiz.build(questions.get(i).getQuestion(), questions.get(i).isAnswer());
            quizList.add(quiz);
        }

        member.setQuizList(quizList);

        memberRepository.save(member);
    }

    @Transactional
    public QuizResponse randomSampling(){

        Member member = memberRepository.randomSampling();

        if(member == null){
            return null;
        }

        List<Quiz> quizList = member.getQuizList();

        QuizResponse quizResponse = QuizResponse.build(member.getMemberId(), quizList);

        return quizResponse;
    }
}
