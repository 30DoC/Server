package com.thirty.api.controller;

import com.thirty.api.domain.Member;
import com.thirty.api.service.LoginService;
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
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "login", notes = "login (return member status)")
    @RequestMapping(value = "login/{uniqueKey}", method = RequestMethod.POST)
    public boolean login(@PathVariable String uniqueKey){

        Member member = loginService.findByUniqueKey(uniqueKey);

        if(member == null){

        }

        return true;
    }
}
