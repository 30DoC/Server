package com.thirty.api.controller;

import com.thirty.api.domain.Member;
import com.thirty.api.dto.StatusResponse;
import com.thirty.api.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ByeongChan on 2018. 1. 16..
 */

@CrossOrigin(origins = "*")
@Api(value = "Login API", description = "Login API", basePath = "/api/v1/login")
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "login", notes = "로그인 성공 시 회원 id값을 리턴하고 실패 시 -1을 리턴합니다.")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Long login(@RequestBody String uniqueKey){

        Member member = memberService.findByUniqueKey(uniqueKey);

        if(member == null) {
            Member savedMember = memberService.save(uniqueKey);

            return savedMember.getMemberId();
        } else{
            return -1L;
        }
    }
}
