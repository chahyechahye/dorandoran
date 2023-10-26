package com.doran.raw_voice.service;

import com.doran.exception.dto.CustomException;
import com.doran.exception.dto.ErrorCode;
import com.doran.raw_voice.RawVoiceMapper;
import com.doran.raw_voice.entity.RawVoice;
import com.doran.raw_voice.repository.RawVoiceRepository;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RawVoiceService {
    private final BucketMapper bucketMapper; // Google cloud domain url , UUID
    private final BucketService bucketService;
    private final RawVoiceRepository rawVoiceRepository;
    private final RawVoiceMapper rawVoiceMapper;

    public RawVoice findRawVoiceById(int rvId){
        return rawVoiceRepository.findById(rvId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
    }

}
