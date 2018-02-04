package com.thirty.api.response;

import lombok.*;

/**
 * Created by ByeongChan on 2018. 2. 4..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponse {
    /**
     * 사용자 STATUS 리턴
     */

    private String status;

    public static StatusResponse build(String status){
        return StatusResponse.builder()
                .status(status)
                .build();
    }
}
