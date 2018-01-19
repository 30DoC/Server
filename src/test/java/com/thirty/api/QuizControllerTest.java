package com.thirty.api;

/**
 * Created by ByeongChan on 2018. 1. 19..
 */

import com.thirty.api.service.QuizService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizControllerTest {

    @Autowired
    private QuizService quizService;

    @Test
    public void saveTest(){

    }
}
