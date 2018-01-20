package com.thirty.api;

import com.thirty.api.domain.Member;
import com.thirty.api.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        Member member = Member.build("uniqueTest", false);
        loginService.save(member);
    }
}
