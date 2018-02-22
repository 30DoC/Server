package com.thirty.api.response;

import lombok.*;

/**
 * Created by ByeongChan on 2018. 2. 22..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {

    /**
     * 결과 내용을 담아서 리턴
     */

    private String result;

    public static ResultResponse build(String result){
        return ResultResponse.builder()
                .result(result)
                .build();
    }
}
