package com.thirty.api.persistence;

import com.thirty.api.domain.ChatRoom;
import com.thirty.api.domain.ChatVoice;
import com.thirty.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 2. 1..
 */

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
