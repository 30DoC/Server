package com.thirty.api.persistence;

import com.thirty.api.domain.ChatVoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 2. 3..
 */

@Repository
public interface ChatVoiceRepository extends JpaRepository<ChatVoice, Long>{
}
