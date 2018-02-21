package com.thirty.api.response;

import lombok.*;

/**
 * Created by ByeongChan on 2018. 2. 21..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomIdResponse {

    /**
     * 채팅방 ID 리턴
     */

    private Long roomId;

    public static RoomIdResponse build(Long roomId){
        return RoomIdResponse.builder()
                .roomId(roomId)
                .build();
    }
}
