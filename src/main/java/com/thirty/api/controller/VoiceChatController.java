package com.thirty.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ByeongChan on 2018. 1. 30..
 */

@CrossOrigin(origins = "*")
@Api(value = "Voice Chat API", description = "Voice Chat API", basePath = "/api/v1/chat")
@RestController
@RequestMapping("/api/v1/chat")
public class VoiceChatController {

    @ApiOperation(value = "voice", notes = "상대방에게 음성 파일을 전송합니다")
    @RequestMapping(value = "sendVoice", method = RequestMethod.POST)
    public String sendVoice(@RequestPart MultipartFile files){

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension;
        return null;
    }
}
