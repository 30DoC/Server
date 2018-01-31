package com.thirty.api.controller;

import com.thirty.api.service.QuizService;
import com.thirty.api.domain.Quiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "quiz", notes = "채팅 중이 아닌 사용자들을 대상으로 무작위로 선택하여 퀴즈 목록을 리턴합니다.")
    @RequestMapping(value = "randomQuiz", method = RequestMethod.GET)
    public List<Quiz> randomQuiz(){

        // 컨트롤러에서는 최대한 해당 요청에 맞는 비즈니스 연결과 Exception처리만 ?

        return quizService.randomSampling();
    }

    @ApiOperation(value = "answer", notes = "사용자가 답변을 입력한 퀴즈의 정답률을 리턴합니다.")
    @RequestMapping(value = "answer", method = RequestMethod.POST)
    public int correctAnswer(){

        return 0;
    }
}
