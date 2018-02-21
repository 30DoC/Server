package com.thirty.api.service;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.dto.QuizForm;
import com.thirty.api.dto.RegistQuizForm;
import com.thirty.api.dto.SubmitAnswer;
import com.thirty.api.persistence.MemberRepository;
import com.thirty.api.persistence.QuizRepository;
import com.thirty.api.response.QuizResponse;
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
    public String saveQuiz(RegistQuizForm registQuizForm){

        Long memberId = registQuizForm.getUserId();
        List<QuizForm> quizList = registQuizForm.getQuizFormList();

        // 퀴즈 등록
        Member member = memberRepository.findOne(memberId);
        List<Quiz> savedQuizList = member.getQuizList();

        for (int i = 0; i < quizList.size(); i++) {
            // 등록할 질문과 답변
            String registQ = quizList.get(i).getQuestion();
            boolean registA = quizList.get(i).isAnswer();

            savedQuizList.get(i).setQuestion(registQ);
            savedQuizList.get(i).setAnswer(registA);
        }

        member.setQuizList(savedQuizList);

        // 사용자 상태 WAITING으로 변경
        member.setStatus("WAITING");
        memberRepository.save(member);

        return member.getStatus();
    }

    @Transactional
    public QuizResponse randomSampling(){

        Member member = memberRepository.randomSampling();

        if(member == null){
            return null;
        }

        List<Quiz> savedQuizList = member.getQuizList();
        List<QuizForm> quizList = new ArrayList<>();

        for (int i = 0; i < savedQuizList.size(); i++) {
            QuizForm quiz = QuizForm.build(savedQuizList.get(i).getQuestion(),
                    savedQuizList.get(i).isAnswer());

            quizList.add(quiz);
        }

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
    public List<QuizForm> selectQuizList(Long userId){

        Member member = memberRepository.findOne(userId);

        List<Quiz> savedQuizList = member.getQuizList();
        List<QuizForm> quizList = new ArrayList<>();

        for (int i = 0; i < savedQuizList.size(); i++) {
            QuizForm quiz = QuizForm.build(savedQuizList.get(i).getQuestion(),
                    savedQuizList.get(i).isAnswer());

            quizList.add(quiz);
        }

        return quizList;
    }
}
