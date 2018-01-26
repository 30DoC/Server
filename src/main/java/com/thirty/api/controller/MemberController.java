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
@RequestMapping("/api/v1/login")
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "login", notes = "login (return member status)")
    @RequestMapping(value = "login/{uniqueKey}", method = RequestMethod.POST)
    public StatusResponse login(@PathVariable String uniqueKey){

        Member member = memberService.findByUniqueKey(uniqueKey);

        if(member == null) {
            return StatusResponse.build(-1);
        }

        if(member.isStatus()){
            return StatusResponse.build(1);
        } else{
            return StatusResponse.build(0);
        }
    }
}
