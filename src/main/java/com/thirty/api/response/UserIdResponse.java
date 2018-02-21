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
public class UserIdResponse {

    /**
     * 사용자 ID 리턴
     */

    private Long userId;

    public static UserIdResponse build(Long userId){
        return UserIdResponse.builder()
                .userId(userId)
                .build();
    }
}
