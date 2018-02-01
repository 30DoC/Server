package com.thirty.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by ByeongChan on 2018. 1. 30..
 */

@CrossOrigin(origins = "*")
@Api(value = "Voice Chat API", description = "Voice Chat API", basePath = "/api/v1/chat")
@Controller
@RequestMapping("/api/v1/chat")
public class VoiceChatController {

    @RequestMapping(value = "sendVoice", method = RequestMethod.GET)
    public String viewVoice(){
        return "voiceDemo";
    }

    @ApiOperation(value = "voice", notes = "상대방에게 음성 파일을 전송합니다")
    @RequestMapping(value = "sendVoice", method = RequestMethod.POST)
    public String sendVoice(@RequestPart MultipartFile files) throws IOException{

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();

        File destinationFile;
        String destinationFileName;
        String fileUrl = "/Users/ByeongChan/Music";

        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        return "redirect:/voiceDemo";
    }
}
