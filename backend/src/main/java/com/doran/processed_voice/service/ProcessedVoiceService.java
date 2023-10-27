package com.doran.processed_voice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.content.service.ContentService;
import com.doran.processed_voice.dto.res.ProcessedVoiceListDto;
import com.doran.processed_voice.dto.res.ProcessedVoiceResDto;
import com.doran.processed_voice.entity.ProcessedVoice;
import com.doran.processed_voice.mapper.ProcessedVoiceMapper;
import com.doran.processed_voice.repository.ProcessedVoiceRepository;
import com.doran.utils.auth.Auth;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessedVoiceService {
    private final ProcessedVoiceMapper processedVoiceMapper;
    private final ProcessedVoiceRepository processedVoiceRepository;
    private final ContentService contentService;

    // 가공된 목소리 검색
    public ProcessedVoiceResDto getProcessedVoiceById(int pvId){ // 사용자 전용
        ProcessedVoice processedVoice = processedVoiceRepository.findById(pvId)
            .orElseThrow(()-> new CustomException(ErrorCode.VOICE_NOT_FOUND));
        return processedVoiceMapper.pvToResDto(processedVoice);
    }

    // 가공된 목소리 조회
    public ProcessedVoiceListDto getProcessedVoiceList(){
        List<ProcessedVoice> processedVoiceList = processedVoiceRepository.findAll();
        List<ProcessedVoiceResDto> processedVoiceResDtoList = processedVoiceMapper.toDtoList(processedVoiceList);
        return new ProcessedVoiceListDto(processedVoiceResDtoList.size(), processedVoiceResDtoList);
    }

}
