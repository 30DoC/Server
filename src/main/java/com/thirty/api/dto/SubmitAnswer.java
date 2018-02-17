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
     * 퀴즈 답안
     * 사용자가 제출한 답
     */
    private boolean answer;

    private boolean submitAnswer;

    public static SubmitAnswer build(boolean answer, boolean submitAnswer){
        return SubmitAnswer.builder()
                .answer(answer)
                .submitAnswer(submitAnswer)
                .build();
    }
}
