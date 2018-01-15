package com.thirty.api.controller;

import com.thirty.api.domain.SampleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ByeongChan on 2018. 1. 15..
 */

@CrossOrigin(origins = "*")
@Api(value = "test API", description = "test API", basePath = "/api/v1/sample")
@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

    @ApiOperation(value = "hello", notes = "print Hello")
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @ApiOperation(value = "make", notes = "make Sample")
    @RequestMapping("/make")
    public SampleVO makeSample(){
        SampleVO vo = new SampleVO();

        vo.setVal1("v1");
        vo.setVal2("v2");

        System.out.println(vo);

        return vo;
    }
}
