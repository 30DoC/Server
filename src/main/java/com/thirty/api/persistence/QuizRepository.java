package com.thirty.api.persistence;

import com.thirty.api.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ByeongChan on 2018. 1. 18..
 */

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
}
