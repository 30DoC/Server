package com.thirty.api.controller;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.dto.StatusResponse;
import com.thirty.api.service.LoginService;
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
    LoginService quizService;

    @ApiOperation(value = "quiz", notes = "quiz (return random quiz)")
    @RequestMapping(value = "randomQuiz", method = RequestMethod.GET)
    public List<Quiz> randomQuiz(){

        // 컨트롤러에서는 최대한 해당 요청에 맞는 비즈니스 연결과 Exception처리만 ?

        return quizService.randomSampling();
    }
}
