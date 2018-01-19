package com.thirty.api.service;

import com.thirty.api.domain.Quiz;
import com.thirty.api.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ByeongChan on 2018. 1. 19..
 */

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    public void save(Quiz quiz){ quizRepository.save(quiz); }
}
