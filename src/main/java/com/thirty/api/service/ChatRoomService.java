package com.thirty.api.service;

import com.thirty.api.domain.ChatRoom;
import com.thirty.api.domain.ChatVoice;
import com.thirty.api.domain.Member;
import com.thirty.api.persistence.ChatRoomRepository;
import com.thirty.api.persistence.MemberRepository;
import com.thirty.api.response.ChatVoiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 2. 10..
 */

@Service
public class ChatRoomService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Long createRoom(Long user1Id, Long user2Id){
        Member user1 = memberRepository.findOne(user1Id);
        Member user2 = memberRepository.findOne(user2Id);

        Long createdRoomId;
        // 만약 상대방이 대기중인 상태라면 채팅방 개설
        if( user2.getStatus().equals("WAITING")){
            ChatRoom createdRoom = chatRoomRepository.save(ChatRoom.build(user1Id, user2Id));

            // user1, user2 status 변경
            user1.setStatus("CHATTING");
            user2.setStatus("CHATTING");

            memberRepository.save(user1);
            memberRepository.save(user2);

            createdRoomId = createdRoom.getRoomId();
        } else{
            createdRoomId = -1L;
        }

        return createdRoomId;
    }

    @Transactional
    public Long quitRoom(Long roomId, Long userId){
        ChatRoom curRoom = chatRoomRepository.findOne(roomId);
        // chatRoom valid 처리?

        // user status 변경
        Member member = memberRepository.findOne(userId);
        member.setStatus("WAITING");
        memberRepository.save(member);

        return curRoom.getRoomId();
    }

    @Transactional
    public Long choice(Long userId){
        Member member = memberRepository.findOne(userId);
        member.setStatus("CHOOSING");

        memberRepository.save(member);

        return member.getMemberId();
    }

    @Transactional
    public Long choiceCancel(Long userId){
        Member member = memberRepository.findOne(userId);
        member.setStatus("WAITING");

        memberRepository.save(member);

        return member.getMemberId();
    }
}
