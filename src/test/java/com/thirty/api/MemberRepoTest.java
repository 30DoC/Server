package com.thirty.api;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.persistence.MemberRepository;
import com.thirty.api.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by ByeongChan on 2018. 1. 24..
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void randomSamplingTest(){
        Member member = memberRepository.randomSampling();

        System.out.println("---------------------------");
        System.out.println(member.getMemberId());
        System.out.println(member.getUniqueKey());
        System.out.println("---------------------------");
    }
}
