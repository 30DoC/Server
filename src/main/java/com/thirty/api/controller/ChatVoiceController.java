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

    @ApiOperation(value = "send voice", notes = "상대방에게 음성 파일을 전송합니다.")
    @RequestMapping(value = "sendVoice", method = RequestMethod.POST)
    public PutObjectResult sendVoice(@RequestParam Long roomId, @RequestParam Long registId, @RequestPart MultipartFile files) throws IOException {

        if(files == null){

        }

        return chatVoiceService.sendVoice(roomId, registId, files);
    }

    @ApiOperation(value = "observe chat room", notes = "채팅 방 ID와 offset을 받으면 갱신 된 채팅 방 내용을 받아옵니다." +
            "리턴 값은 갱신된 가장 최근 offset과 이전 offset ~ 최근 offset 사이의 음성파일 목록을 리턴." +
            "실제로 음성파일이 넘어가는 것이 아닌 음성파일의 목록들만 리턴합니다. ")
    @RequestMapping(value = "observeRoom", method = RequestMethod.POST)
    public ChatVoiceResponse observeRoom(@RequestParam Long roomId, @RequestParam int offset) {
        return chatVoiceService.observeChat(roomId, offset);
    }

    @ApiOperation(value = "download voice", notes = "음성파일을 다운로드 합니다.")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
        return chatVoiceService.download(key);

        // Type 2. 링크로 주는 방법
        // https://s3.ap-northeast-2.amazonaws.com/jobc-bucket-1/testaudio.mp3
    }

    @ApiOperation(value = "bucket list", notes = "버킷 파일 리스트 보기 !!(현재 쓰이지 않음)!!")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<S3ObjectSummary> list() throws IOException {
        return chatVoiceService.list();

    }
}
