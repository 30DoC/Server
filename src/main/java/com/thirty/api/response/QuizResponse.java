package com.thirty.api.response;

import com.thirty.api.dto.QuizForm;
import lombok.*;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 31..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
    /**
     * 질문 등록한 사용자 ID
     * 질문 리스트
     */
    private Long memberId;

    private List<QuizForm> quizList;

    public static QuizResponse build(Long memberId, List<QuizForm> quizList){
        return QuizResponse.builder()
                .memberId(memberId)
                .quizList(quizList)
                .build();
    }
}
