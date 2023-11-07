package com.doran.processed_voice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.content.entity.Content;
import com.doran.content.service.ContentService;
import com.doran.parent.entity.Parent;
import com.doran.parent.repository.ParentRepository;
import com.doran.processed_voice.dto.req.ProcessedVoiceInsertDto;
import com.doran.processed_voice.dto.res.PVQueResDto;
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
import com.doran.utils.common.Genders;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;
import com.doran.utils.rabbitmq.dto.res.VoiceResMessage;

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
    private final ParentRepository parentRepository;
    private final BucketMapper bucketMapper;
    private final BucketService bucketService;

    // 가공된 목소리 검색 - 관리자
    public ProcessedVoiceResDto getProcessedVoiceForAdmin(int userId, int contentId) {
        ProcessedVoice processedVoice = processedVoiceRepository.findVoiceByParentIdAndContentId(userId, contentId)
            .orElseThrow(() -> new CustomException(ErrorCode.VOICE_NOT_FOUND));
        return processedVoiceMapper.pvToResDto(processedVoice);
    }

    // 가공된 목소리 검색 - 아이
    public ProcessedVoiceResDto getProcessedVoiceById(int contentId) { // 아이 전용
        Parent parent = parentRepository.findParentByChildUserId(Auth.getInfo().getUserId())
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        ProcessedVoice processedVoice = processedVoiceRepository.findVoiceByParentIdAndContentId(
                parent.getUser().getId(), contentId)
            .orElseThrow(() -> new CustomException(ErrorCode.VOICE_NOT_FOUND));
        return processedVoiceMapper.pvToResDto(processedVoice);
    }

    // 가공된 목소리 조회
    public ProcessedVoiceListDto getProcessedVoiceList() {
        List<ProcessedVoice> processedVoiceList = processedVoiceRepository.findAll();
        List<ProcessedVoiceResDto> processedVoiceResDtoList = processedVoiceMapper.toDtoList(processedVoiceList);
        return new ProcessedVoiceListDto(processedVoiceResDtoList.size(), processedVoiceResDtoList);
    }

    public void insertProcessedVoice(ProcessedVoiceInsertDto processedVoiceInsertDto) {
        Content content = contentService.getContentById(processedVoiceInsertDto.getContentId());
        User user = userService.findUser(processedVoiceInsertDto.getUserId());
        InsertDto insertDto = bucketMapper.toInsertDto(processedVoiceInsertDto.getFile(), "processed_voice");
        String voiceUrl = bucketService.insertFile(insertDto);
        ProcessedVoice processedVoice = processedVoiceMapper.toProcessedVoice(content, user, voiceUrl, Genders.MALE);
        processedVoiceRepository.save(processedVoice);
    }

    public void saveAll(VoiceResMessage voiceResMessage) {
        int userId = voiceResMessage.getUserId();
        User user = userService.findUser(userId);

        for (PVQueResDto pv : voiceResMessage.getPvList()) {
            Content content = contentService.getContentById(pv.getContentId());
            ProcessedVoice processedVoice = processedVoiceMapper.toProcessedVoice(content, user, pv.getVoiceUrl(), voiceResMessage.getGenders());
            processedVoiceRepository.save(processedVoice);
        }
    }

}
