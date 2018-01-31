package com.thirty.api.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by ByeongChan on 2018. 1. 25..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageVO implements Serializable{

    private String nickName;
    private String message;

    public static MessageVO build(String nickName, String message){
        return MessageVO.builder()
                .nickName(nickName)
                .message(message)
                .build();
    }
}
