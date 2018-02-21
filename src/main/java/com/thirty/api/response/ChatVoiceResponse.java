package com.thirty.api.response;

import com.thirty.api.domain.ChatVoice;
import lombok.*;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 2. 3..
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatVoiceResponse {

    /**
     * offset 값
     * 갱신된 chat voice 리스트
     */

    private int offset;

    private List<ChatVoice> chatVoiceList;

    public static ChatVoiceResponse build(int offset, List<ChatVoice> chatVoiceList){
        return ChatVoiceResponse.builder()
                .offset(offset)
                .chatVoiceList(chatVoiceList)
                .build();
    }
}
