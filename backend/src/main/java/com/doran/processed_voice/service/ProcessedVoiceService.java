package com.doran.processed_voice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.content.entity.Content;
import com.doran.content.service.ContentService;
import com.doran.processed_voice.dto.req.ProcessedVoiceInsertDto;
import com.doran.processed_voice.dto.res.ProcessedVoiceListDto;
import com.doran.processed_voice.dto.res.ProcessedVoiceResDto;
import com.doran.processed_voice.entity.ProcessedVoice;
import com.doran.processed_voice.mapper.ProcessedVoiceMapper;
import com.doran.processed_voice.repository.ProcessedVoiceRepository;
import com.doran.user.entity.User;
import com.doran.user.service.UserService;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.dto.InsertDto;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
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
    private final UserService userService;
    private final BucketService bucketService;



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

    public void insertProcessedVoice(ProcessedVoiceInsertDto processedVoiceInsertDto){
        // 가공된 목소리 저장 이름은 userId_(m/f)_contentId.mp3 로 됨 - 유저아이디_성별_컨텐츠아이디.mp3
        String name = Auth.getInfo().getUserId() + "_" + processedVoiceInsertDto.getVoiceType() + "_" + processedVoiceInsertDto.getContentId()+ ".mp3";
        Content content = contentService.getContentById(processedVoiceInsertDto.getContentId());
        User user = userService.findUser(Auth.getInfo().getUserId());

        InsertDto insertDto = new InsertDto(processedVoiceInsertDto.getVoice(), name);
        String voiceUrl = bucketService.insertFile(insertDto);
        ProcessedVoice processedVoice = processedVoiceMapper.toProcessedVoice(content,user,voiceUrl);
        processedVoiceRepository.save(processedVoice);
    }

}
