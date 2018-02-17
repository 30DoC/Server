package com.thirty.api.controller;

import com.thirty.api.common.NotFoundException;
import com.thirty.api.domain.ChatRoom;
import com.thirty.api.service.ChatRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ByeongChan on 2018. 2. 10..
 */

@CrossOrigin(origins = "*")
@Api(value = "Chat Room API", description = "Chat Room API", basePath = "/api/v1/chatRoom")
@RestController
@RequestMapping("/api/v1/chatRoom")
public class ChatRoomController {

    @Autowired
    ChatRoomService chatRoomService;

    @ApiOperation(value = "create room", notes = "두 사용자의 ID user1, user2를 받아서 채팅 방이 개설됩니다. 리턴 값은 개설된 방 ID")
    @RequestMapping(value = "createRoom", method = RequestMethod.POST)
    public Long createRoom(@RequestParam Long user1Id, @RequestParam Long user2Id) {

        // Exception 처리

        // 상대방에게 push 보내야 함
        ChatRoom createdRoom = chatRoomService.createRoom(user1Id, user2Id);

        if(createdRoom == null){
            throw new NotFoundException();
        }

        return createdRoom.getRoomId();
    }

    @ApiOperation(value = "quit room", notes = "사용자 ID와 채팅 방 ID를 받아서 채팅 방을 나갑니다.")
    @RequestMapping(value = "quitRoom", method = RequestMethod.POST)
    public void quitRoom(@RequestParam Long roomId, @RequestParam Long userId) {

        // Exception 처리
        chatRoomService.quitRoom(roomId, userId);
    }

    @ApiOperation(value = "choice", notes = "사용자가 채팅을 선택하고 있는 화면. 사용자의 상태를 CHOOSING으로 변경합니다.")
    @RequestMapping(value = "choice", method = RequestMethod.POST)
    public void choice(@RequestParam Long userId) {

        // 선택할 때 납치안해가는게 좋을듯

        // Exception 처리
        chatRoomService.choice(userId);
    }
}
