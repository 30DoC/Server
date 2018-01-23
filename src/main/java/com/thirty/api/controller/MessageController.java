package com.thirty.api.controller;

import io.swagger.annotations.Api;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ByeongChan on 2018. 1. 23..
 */

@CrossOrigin(origins = "*")
@Api(value = "Message API", description = "Message API", basePath = "/api/v1/message")
@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    @MessageMapping("/hello") // 해당 매핑 주소에 메시자가 도착하면 이 메소드가 실행된다.
    @SendTo("/topic/roomId") // 이 메소드에서 반환된 객체를 유저에게 전달
    public Message broadcasting(Message message) throws Exception{
        System.out.println("message : " + message.toString());
        return message;
    }

    @MessageMapping("/out")
    @SendTo("/topic/out")
    public String outroom(String message) throws Exception{
        System.out.println("out message : " + message);
        return message;
    }

    @MessageMapping("/in")
    @SendTo("/topic/in")
    public String inroom(String message) throws Exception{
        System.out.println("in message : " + message);
        return message;
    }
}
