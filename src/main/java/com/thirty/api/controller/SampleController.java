package com.thirty.api.controller;

import com.thirty.api.domain.SampleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "save", notes = "save Sample")
    @RequestMapping(value = "save/{val1}", method = RequestMethod.POST)
    public SampleVO saveSample(@PathVariable String val1){
        SampleVO vo = SampleVO.build(val1);

        System.out.println(vo);

        return vo;
    }
}
