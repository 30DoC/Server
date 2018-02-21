package com.thirty.api.controller;

import com.thirty.api.dto.QuizForm;
import com.thirty.api.dto.RegistQuizForm;
import com.thirty.api.dto.SubmitAnswer;
import com.thirty.api.response.QuizResponse;
import com.thirty.api.response.StatusResponse;
import com.thirty.api.service.QuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "random quiz", notes = "상태가 'WAITTING'인 사용자들을 대상으로 무작위로 선택하여 사용자 아이디와 퀴즈 목록을 리턴합니다.")
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

    @ApiOperation(value = "show user quiz list", notes = "사용자가 등록한 질문 10개를 리턴합니다. 질문을 등록하지 않았거나 빈 질문은 NULL로 리턴됩니다.")
    @RequestMapping(value = "inquireQuiz", method = RequestMethod.POST)
    public List<QuizForm> inquireQuiz(@RequestParam Long userId){

        List<QuizForm> quizList = quizService.selectQuizList(userId);

        return quizList;
    }

    @ApiOperation(value = "quiz regist", notes = "사용자가 질문을 등록하는 API. 저장방식이 아닌 데이터베이스 업데이트 방식이므로" +
            "퀴즈 목록을 조회한 데이터 형식 그대로 quiz form 리스트를 보내야한다." +
            "\n 리턴 값은 질문을 등록한 후의 사용자 상태.")
    @RequestMapping(value = "registQuiz", method = RequestMethod.POST)
    public StatusResponse registQuiz(@ApiParam("질문을 등록하는 userID / 등록할 질문 10개의 리스트")
                                 @RequestBody RegistQuizForm registQuizForm){

        StatusResponse statusResponse;

        try {
            String status = quizService.saveQuiz(registQuizForm);
            statusResponse = StatusResponse.build(status);
        } catch (Exception e){
            e.printStackTrace();
            statusResponse = StatusResponse.build("NONE");
        }

        // 질문을 다 지우고 실행시켰을 경우 사용자를 다시 NONE 상태로 바꿔주어야 한다.
        return statusResponse;
    }
}
