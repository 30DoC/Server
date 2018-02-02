package com.thirty.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ByeongChan on 2018. 2. 1..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="chatVoice")
public class ChatVoice implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long voiceId;

    @Column
    private Long roomId; // Foreign Key

    @Column
    private String filename;

    @Column
    private String fileurl;

    @Column
    private Long registId;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date regdate;

    public static ChatVoice build(Long roomId, String filename, String fileurl, Long registId) {
        return ChatVoice.builder()
                .roomId(roomId)
                .filename(filename)
                .fileurl(fileurl)
                .registId(registId)
                .build();
    }
}
