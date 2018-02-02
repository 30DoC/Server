package com.thirty.api.service;

import com.thirty.api.domain.ChatRoom;
import com.thirty.api.domain.ChatVoice;
import com.thirty.api.persistence.ChatRoomRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Long createRoom(Long user1Id, Long user2Id){
        ChatRoom createRoom = chatRoomRepository.save(ChatRoom.build(user1Id, user2Id));

        return createRoom.getRoomId();
    }

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

        ChatVoice chatVoice = ChatVoice.build(roomId, destinationFileName, fileUrl, registId);
        List<ChatVoice> chatVoiceList = new ArrayList<>();
        chatVoiceList.add(chatVoice);

        ChatRoom chatRoom = chatRoomRepository.findOne(roomId);
        chatRoom.setChatVoiceList(chatVoiceList);
    }
}
