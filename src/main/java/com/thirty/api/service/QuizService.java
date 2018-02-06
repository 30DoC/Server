package com.thirty.api.service;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.dto.QuizRequest;
import com.thirty.api.response.QuizResponse;
import com.thirty.api.dto.SubmitAnswer;
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

    @Transactional
    public int calPercentCA(List<SubmitAnswer> submits){
        int correctCnt = 0;

        for (int i = 0; i < submits.size(); i++) {
            if(submits.get(i).isAnswer() == submits.get(i).isSubmitAnswer()){
                correctCnt++;
            }
        }

        int percentCA = (int)(((double)correctCnt / (double)submits.size()) * 100.0);

        return percentCA;
    }

    @Transactional
    public List<Quiz> selectQuizList(Long userId){

        Member member = memberRepository.findOne(userId);

        return member.getQuizList();
    }
}
