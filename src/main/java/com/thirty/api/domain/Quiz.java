package com.thirty.api.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by ByeongChan on 2018. 1. 18..
 */

@Setter
@Getter
@ToString(exclude = "member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long quizId;

    @ManyToOne
    @Column
    private Member member;

    @Column
    private String question;

    @Column
    private boolean answer;

    public static Quiz build(Member member, String question, boolean answer) {
        return Quiz.builder()
                .member(member)
                .question(question)
                .answer(answer)
                .build();
    }
}
