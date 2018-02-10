package com.thirty.api.controller;

import com.thirty.api.domain.ChatRoom;
import com.thirty.api.response.ChatVoiceResponse;
import com.thirty.api.service.VoiceChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by ByeongChan on 2018. 1. 30..
 */

@CrossOrigin(origins = "*")
@Api(value = "Voice Chat API", description = "Voice Chat API", basePath = "/api/v1/chat")
@RestController
@RequestMapping("/api/v1/chat")
public class VoiceChatController {

    @Autowired
    VoiceChatService voiceChatService;

    // 컨트롤러 나눠야 할 듯


    @ApiOperation(value = "voice", notes = "상대방에게 음성 파일을 전송합니다")
    @RequestMapping(value = "sendVoice", method = RequestMethod.POST)
    public String sendVoice(@RequestParam Long roomId, @RequestParam Long registId, @RequestPart MultipartFile files) throws IOException{

        voiceChatService.sendVoice(roomId, registId, files);

        return "success";
    }

    @ApiOperation(value = "observe chat room", notes = "채팅 방 ID와 offset을 받으면 갱신 된 채팅 방 내용을 받아옵니다.")
    @RequestMapping(value = "observeRoom", method = RequestMethod.POST)
    public ChatVoiceResponse observeRoom(@RequestParam Long roomId, @RequestParam int offset) {

        //return voiceChatService.observeRoom(roomId, offset);
        return null;
    }
}
