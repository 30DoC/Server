package com.thirty.api;

import com.thirty.api.domain.Member;
import com.thirty.api.domain.Quiz;
import com.thirty.api.persistence.MemberRepository;
import com.thirty.api.service.MemberService;
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
public class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void loginTest(){
        System.out.println(memberService.save("testUniqueKey"));
    }

    @Test
    public void insertQuiz(){
        Member member = Member.build("unium", false);

        Quiz quiz1 = Quiz.build("q1", false);
        Quiz quiz2 = Quiz.build("q2", true);
        Quiz quiz3 = Quiz.build("q2", false);
        Quiz quiz4 = Quiz.build("q2", true);

        member.setQuizList(Arrays.asList(quiz1, quiz2, quiz3, quiz4));

        memberRepository.save(member);
    }
}
