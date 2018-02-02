package com.thirty.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 2. 1..
 */

@Setter
@Getter
@ToString(exclude = "chatVoiceList")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="chatRoom")
public class ChatRoom implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long roomId;

    @Column
    private Long user1Id;

    @Column
    private Long user2Id;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date regdate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="roomId")
    private List<ChatVoice> chatVoiceList;

    public static ChatRoom build(Long user1Id, Long user2Id) {
        return ChatRoom.builder()
                .user1Id(user1Id)
                .user2Id(user2Id)
                .build();
    }
}
