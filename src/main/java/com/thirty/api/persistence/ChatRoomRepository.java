package com.thirty.api.persistence;

import com.thirty.api.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 2. 1..
 */

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
