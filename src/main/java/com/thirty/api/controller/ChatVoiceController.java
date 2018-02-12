package com.thirty.api.controller;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.thirty.api.response.ChatVoiceResponse;
import com.thirty.api.service.ChatVoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 30..
 */

@CrossOrigin(origins = "*")
@Api(value = "Chat Voice API", description = "Chat Voice API", basePath = "/api/v1/chatVoice")
@RestController
@RequestMapping("/api/v1/chatVoice")
public class ChatVoiceController {

    @Autowired
    ChatVoiceService chatVoiceService;

    @ApiOperation(value = "voice", notes = "상대방에게 음성 파일을 전송합니다.")
    @RequestMapping(value = "sendVoice", method = RequestMethod.POST)
    public PutObjectResult sendVoice(@RequestParam Long roomId, @RequestParam Long registId, @RequestPart MultipartFile files) throws IOException {

        return chatVoiceService.sendVoice(roomId, registId, files);
    }

    @ApiOperation(value = "observe chat room", notes = "채팅 방 ID와 offset을 받으면 갱신 된 채팅 방 내용을 받아옵니다.")
    @RequestMapping(value = "observeRoom", method = RequestMethod.POST)
    public ChatVoiceResponse observeRoom(@RequestParam Long roomId, @RequestParam int offset) {
        //return voiceChatService.observeRoom(roomId, offset);
        return null;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
        return chatVoiceService.download(key);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<S3ObjectSummary> list() throws IOException {
        return chatVoiceService.list();

    }
}
