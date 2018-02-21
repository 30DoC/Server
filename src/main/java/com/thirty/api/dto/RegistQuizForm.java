package com.thirty.api.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 2. 21..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistQuizForm implements Serializable {

    /**
     * 질문 등록할 유저 ID
     * 등록 질문 리스트
     */

    private Long userId;

    private List<QuizForm> quizFormList;

    public static RegistQuizForm build(Long userId, List<QuizForm> quizFormList){
        return RegistQuizForm.builder()
                .userId(userId)
                .quizFormList(quizFormList)
                .build();
    }
}
