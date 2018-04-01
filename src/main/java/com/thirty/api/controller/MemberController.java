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

    @ApiOperation(value = "login", notes = "로그인 성공 시 회원 id값을 리턴하고 실패 시 -1을 리턴합니다." +
            "\n 최초 사용자는 NULL값이 담긴 질문 목록 10개가 생성이 되고 상태는 'NONE'이 됩니다.")
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

    @ApiOperation(value = "check member status", notes = "현재 사용자의 상태를 조회합니다." +
            "\n NONE : 최초의 사용자 상태 OR 질문이 등록되지 않은 상태 OR 내 질문이 상대방에게 가는걸 원치 않을 경우" +
            "\n / WAITING : 질문을 등록하고 유저들에게 내 질문이 공개되어 있는 상태 OR 질문이 등록되면 WAITING으로 변경됨" +
            "\n / CHOOSING : 내가 상대방의 문제를 다 풀고 상대방과 대화를 할지 말지 선택하고 있는 상태 OR 이 상태일땐 다른 유저가 나를 낚아채가지 못하는 상태" +
            "\n / CHATTING : 채팅 중인 상태")
    @RequestMapping(value = "observeStatus", method = RequestMethod.POST)
    public StatusResponse observeStatus(@RequestParam Long userId){

        Member member = memberService.findByMemberId(userId);

        if(member == null){ // 예외처리
            // HTTP STATUS (404) OR String ?
        }

        StatusResponse statusResponse = StatusResponse.build(member.getStatus().name());

        return statusResponse;
    }
}
