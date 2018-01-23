package com.thirty.api;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by ByeongChan on 2018. 1. 19..
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void saveTest(){
        Member member = Member.build("uniqueTest1", false);
        loginService.save(member);
    }

    @Test
    public void insertQuiz(){
        Member member = Member.build("uniuni12", false);

        Quiz quiz1 = Quiz.build("q1", false);
        Quiz quiz2 = Quiz.build("q2", true);

        member.setQuizList(Arrays.asList(quiz1, quiz2));

        loginService.save(member);
    }
}
