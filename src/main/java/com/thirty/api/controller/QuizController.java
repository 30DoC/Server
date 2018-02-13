package com.thirty.api.controller;

import com.thirty.api.dto.QuizForm;
import com.thirty.api.dto.SubmitAnswer;
import com.thirty.api.response.QuizResponse;
import com.thirty.api.service.QuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 18..
 */

@CrossOrigin(origins = "*")
@Api(value = "Quiz API", description = "Quiz API", basePath = "/api/v1/quiz")
@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @ApiOperation(value = "random quiz", notes = "채팅 중이 아닌 사용자들을 대상으로 무작위로 선택하여 사용자 아이디와 퀴즈 목록을 리턴합니다.")
    @RequestMapping(value = "randomQuiz", method = RequestMethod.GET)
    public QuizResponse randomQuiz(){

        // 컨트롤러에서는 최대한 해당 요청에 맞는 비즈니스 연결과 Exception처리만 ?

        return quizService.randomSampling();
    }

    @ApiOperation(value = "submit quiz answer", notes = "사용자가 질문에 대합 답변을 제출하면 퀴즈의 정답률을 리턴합니다.")
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public int correctAnswer(@RequestBody List<SubmitAnswer> submits){

        int percentCA = quizService.calPercentCA(submits);

        return percentCA;
    }

    @ApiOperation(value = "show user quiz list", notes = "사용자가 등록한 질문 목록을 리턴합니다.")
    @RequestMapping(value = "inquireQuiz", method = RequestMethod.POST)
    public List<QuizForm> inquireQuiz(@RequestParam Long userId){

        List<QuizForm> quizList = quizService.selectQuizList(userId);

        return quizList;
    }

    @ApiOperation(value = "quiz regist", notes = "사용자가 질문을 등록하는 API")
    @RequestMapping(value = "registQuiz", method = RequestMethod.POST)
    public String registQuiz(@RequestParam Long userId, @RequestBody List<QuizForm> quizList){

        try {
            quizService.saveQuiz(userId, quizList);

            return "success";
        } catch (Exception e){
            e.printStackTrace();

            return "fail";
        }
    }
}
