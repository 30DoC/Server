package com.blur.api.controller;

import com.blur.api.domain.Member;
import com.blur.api.service.MemberService;
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
    public Long login(@RequestBody String uniqueKey){

        Member member = memberService.findByUniqueKey(uniqueKey);

        if(member == null) {
            Member savedMember = memberService.save(uniqueKey);

            return savedMember.getMemberId();
        } else{
            return -1L;
        }
    }

    @ApiOperation(value = "check member status", notes = "현재 사용자의 상태를 조회합니다. 채팅 중인지 아닌지를 리턴합니다 ")
    @RequestMapping(value = "observeStatus", method = RequestMethod.POST)
    public boolean observeStatus(@RequestBody Long memberId){

        Member member = memberService.findByMemberId(memberId);

        if(member == null){ // 예외처리

        }

        return member.isStatus();
    }
}
