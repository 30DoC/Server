package com.blur.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ByeongChan on 2018. 1. 18..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "quiz")
public class Quiz implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long quizId;

    @Column
    private String question;

    @Column
    private boolean answer;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date regdate;

    @Column
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date updatedate;

    public static Quiz build(String question, boolean answer) {
        return Quiz.builder()
                .question(question)
                .answer(answer)
                .build();
    }
}
