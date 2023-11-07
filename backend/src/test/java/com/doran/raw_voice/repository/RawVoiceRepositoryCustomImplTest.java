package com.doran.raw_voice.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.utils.common.Genders;

@SpringBootTest
class RawVoiceRepositoryCustomImplTest {
    @Autowired
    RawVoiceRepository rawVoiceRepository;

    @Test
    @DisplayName("원본목소리_조회_아이디_성별")
    public void 원본목소리_조회_아이디_성별() {
        List<RawVoiceResDto> rawVoiceByUserId = rawVoiceRepository.findRawVoiceByUserId(3, Genders.MALE);

        for (RawVoiceResDto r : rawVoiceByUserId) {
            System.out.println(r.getRvId());
        }
    }

}
