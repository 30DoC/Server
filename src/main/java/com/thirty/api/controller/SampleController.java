package com.thirty.api.controller;

import com.thirty.api.domain.SampleVO;
import com.thirty.api.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 15..
 */

@CrossOrigin(origins = "*")
@Api(value = "test API", description = "test API", basePath = "/api/v1/sample")
@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

    @Autowired
    SampleService sampleService;

    @ApiOperation(value = "findAll", notes = "select all sampleVO")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<SampleVO> findAll(){ return sampleService.findAll(); }

    @ApiOperation(value = "save", notes = "save Sample")
    @RequestMapping(value = "save/{val1}", method = RequestMethod.POST)
    public String saveSample(@PathVariable String val1){
        try {
            sampleService.save(SampleVO.build(val1));
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save fail";
        }
    }
}
