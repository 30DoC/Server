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
public class SubmitAnswer {
    /**
     * 데이터베이스 상대방 ID
     * 퀴즈 답안
     * 사용자가 제출한 답
     */
    private Long quizId;

    private boolean answer;

    private boolean submitAnswer;

    public static SubmitAnswer build(Long quizId, boolean answer, boolean submitAnswer){
        return SubmitAnswer.builder()
                .quizId(quizId)
                .answer(answer)
                .submitAnswer(submitAnswer)
                .build();
    }
}
