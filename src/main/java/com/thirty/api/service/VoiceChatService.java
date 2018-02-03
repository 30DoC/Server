package com.thirty.api.service;

import com.thirty.api.domain.ChatRoom;
import com.thirty.api.domain.ChatVoice;
import com.thirty.api.domain.Member;
import com.thirty.api.dto.ChatVoiceResponse;
import com.thirty.api.persistence.ChatRoomRepository;
import com.thirty.api.persistence.ChatVoiceRepository;
import com.thirty.api.persistence.MemberRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 2. 1..
 */

@Service
public class VoiceChatService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatVoiceRepository chatVoiceRepository;

    @Autowired
    MemberRepository memberRepository;

    public ChatRoom createRoom(Long user1Id, Long user2Id){
        ChatRoom createdRoom = chatRoomRepository.save(ChatRoom.build(user1Id, user2Id));

        return createdRoom;
    }

    @Transactional
    public void quitRoom(Long roomId, Long userId){
        ChatRoom curRoom = chatRoomRepository.findOne(roomId);
        // chatRoom valid 처리?

        Member member = memberRepository.findOne(userId);
        member.setStatus("WAITING");
        memberRepository.save(member);
    }

    @Transactional
    public void sendVoice(Long roomId, Long registId, MultipartFile files) throws IOException{
        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();

        File destinationFile;
        String destinationFileName;
        String fileUrl = "/Users/ByeongChan/";

        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        ///////////////////////////////////////
        //////////// DB 저장
        ///////////////////////////////////////

        ChatRoom chatRoom = chatRoomRepository.findOne(roomId);
        ChatVoice chatVoice = ChatVoice.build(chatRoom.getRoomId(), destinationFileName, fileUrl, registId);

        chatVoiceRepository.save(chatVoice);

        /// 상대방에게 PUSH 알림?
    }

    @Transactional
    public ChatVoiceResponse observeRoom(Long roomId, int offset){
        ChatRoom chatRoom = chatRoomRepository.findOne(roomId);
        List<ChatVoice> chatVoiceList = chatRoom.getChatVoiceList();

        List<ChatVoice> resultVoiceList = new ArrayList<>();

        for (int i = offset; i < chatVoiceList.size(); i++) {
            resultVoiceList.add(chatVoiceList.get(i));
        }

        ChatVoiceResponse chatVoiceResponse = ChatVoiceResponse.build(chatVoiceList.size(), resultVoiceList);

        return chatVoiceResponse;
    }
}
