package com.thirty.api.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by ByeongChan on 2018. 1. 31..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitAnswer implements Serializable{
    /**
     * 데이터베이스 상대방 ID
     * 퀴즈 답안
     * 사용자가 제출한 답
     */
    private Long userId;

    private boolean answer;

    private boolean submitAnswer;

    public static SubmitAnswer build(Long userId, boolean answer, boolean submitAnswer){
        return SubmitAnswer.builder()
                .userId(userId)
                .answer(answer)
                .submitAnswer(submitAnswer)
                .build();
    }
}
