package com.thirty.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ByeongChan on 2018. 1. 16..
 */

@CrossOrigin(origins = "*")
@Api(value = "Login API", description = "Login API", basePath = "/api/v1/login")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

}
