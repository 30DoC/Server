package com.blur.api;

/**
 * Created by ByeongChan on 2018. 1. 19..
 */

import com.blur.api.domain.Quiz;
import com.blur.api.service.QuizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizControllerTest {

    @Autowired
    private QuizService quizService;

    @Test
    public void randomSamplingTest(){
        List<Quiz> quizlist = quizService.randomSampling();

        for (int i = 0; i < quizlist.size(); i++) {
            System.out.println("------------------------------");
            System.out.println(quizlist.get(i).getQuestion());
            System.out.println(quizlist.get(i).isAnswer());
        }
    }
}
