package com.thirty.api.dto;

import lombok.*;

/**
 * Created by ByeongChan on 2018. 1. 31..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequest {

    /**
     * 질문 String
     * 답 boolean
     */
    private String question;

    private boolean answer;

    public static QuizRequest build(String question, boolean answer){
        return QuizRequest.builder()
                .question(question)
                .answer(answer)
                .build();
    }
}
