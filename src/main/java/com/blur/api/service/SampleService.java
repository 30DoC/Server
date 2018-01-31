package com.blur.api.service;

import com.blur.api.domain.SampleVO;
import com.blur.api.persistence.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ByeongChan on 2018. 1. 15..
 */

@Service
public class SampleService {
    @Autowired
    SampleRepository sampleRepository;

    public void save(SampleVO vo) {
        sampleRepository.save(vo);
    }

    public SampleVO findByVal1(String val1) {
        return sampleRepository.findByVal1(val1);
    }

    public List<SampleVO> findAll() {
        return sampleRepository.findAll();
    }
}
