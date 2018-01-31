package com.thirty.api.dto;

import com.thirty.api.domain.Quiz;
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

    private List<Quiz> quizList;

    public static QuizResponse build(Long memberId, List<Quiz> quizList){
        return QuizResponse.builder()
                .memberId(memberId)
                .quizList(quizList)
                .build();
    }
}
