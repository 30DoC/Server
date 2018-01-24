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

        Member member = quizService.randomSampling();

        if(member == null) { // Exception 처리

        }

        if(member.isStatus()){
            return StatusResponse.build(1);
        } else{
            return StatusResponse.build(0);
        }
    }
}
