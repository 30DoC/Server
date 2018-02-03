package com.thirty.api.controller;

import com.thirty.api.domain.ChatRoom;
import com.thirty.api.service.VoiceChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/api/v1/chat")
public class VoiceChatController {

    @Autowired
    VoiceChatService voiceChatService;

    @ApiOperation(value = "create room", notes = "두 사용자의 ID user1, user2를 받아서 채팅 방이 개설됩니다. 리턴 값은 개설된 방 ID")
    @RequestMapping(value = "createRoom", method = RequestMethod.POST)
    public @ResponseBody Long createRoom(@RequestParam Long user1Id, @RequestParam Long user2Id) {

        // Exception 처리

        ChatRoom createdRoom = voiceChatService.createRoom(user1Id, user2Id);

        return createdRoom.getRoomId();
    }

    @ApiOperation(value = "voice", notes = "상대방에게 음성 파일을 전송합니다")
    @RequestMapping(value = "sendVoice", method = RequestMethod.POST)
    public @ResponseBody String sendVoice(@RequestParam Long roomId, @RequestParam Long registId, @RequestPart MultipartFile files) throws IOException{

        voiceChatService.sendVoice(roomId, registId, files);

        return "success";
    }
}
