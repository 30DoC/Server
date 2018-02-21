package com.thirty.api.controller;

import com.thirty.api.domain.Member;
import com.thirty.api.response.StatusResponse;
import com.thirty.api.response.UserIdResponse;
import com.thirty.api.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ByeongChan on 2018. 1. 16..
 */

@CrossOrigin(origins = "*")
@Api(value = "Member API", description = "Member API", basePath = "/api/v1/member")
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "login", notes = "로그인 성공 시 회원 id값을 리턴하고 실패 시 -1을 리턴합니다.")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public UserIdResponse login(@RequestParam String uniqueKey){

        Member member = memberService.findByUniqueKey(uniqueKey);
        UserIdResponse userIdResponse;

        if(member == null) {
            Long savedMemberId = memberService.save(uniqueKey);
            userIdResponse = UserIdResponse.build(savedMemberId);
            // 제대로 멤버가 생성되지 않았을 경우 예외처리 ?

        } else{
            userIdResponse = UserIdResponse.build(-1L);
        }

        return userIdResponse;
    }

    @ApiOperation(value = "check member status", notes = "현재 사용자의 상태를 조회합니다.")
    @RequestMapping(value = "observeStatus", method = RequestMethod.POST)
    public StatusResponse observeStatus(@RequestParam Long userId){

        Member member = memberService.findByMemberId(userId);

        if(member == null){ // 예외처리
            // HTTP STATUS (404) OR String ?
        }

        StatusResponse statusResponse = StatusResponse.build(member.getStatus());

        return statusResponse;
    }
}
