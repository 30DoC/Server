package com.thirty.api.dto;

import lombok.*;

/**
 * Created by ByeongChan on 2018. 1. 17..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponse {
    /**
     * 아이디 존재 X : -1
     * 아이디 존재 O 채팅 X : 0
     * 아이디 존재 O 채팅 O : 1
     */
    private int status;

    public static StatusResponse build(int status){
        return StatusResponse.builder()
                .status(status)
                .build();
    }
}
