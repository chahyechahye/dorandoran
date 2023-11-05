package com.doran.dummy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import com.doran.raw_voice.repository.RawVoiceRepository;
import com.doran.utils.bucket.dto.InsertDto;
import com.doran.utils.bucket.service.BucketService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BucketTest {
    @Autowired
    RawVoiceRepository rawVoiceRepository;

    @Test
    public void insertTest() {
        List<RawVoiceResDto> rawVoiceList = rawVoiceRepository.findRawVoiceByUserId(3);

        for (RawVoiceResDto r : rawVoiceList)
        {
            log.info(r.getVoiceUrl());
        }
    }
}
