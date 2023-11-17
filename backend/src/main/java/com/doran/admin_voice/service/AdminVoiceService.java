package com.doran.admin_voice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.admin_voice.dto.req.AdminVoiceInsertDto;
import com.doran.admin_voice.dto.res.AdminFindResDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.admin_voice.entity.AdminVoice;
import com.doran.admin_voice.mapper.AdminVoiceMapper;
import com.doran.admin_voice.repository.AdminVoiceRepository;
import com.doran.content.entity.Content;
import com.doran.content.service.ContentService;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.common.Genders;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminVoiceService {
    private final AdminVoiceRepository adminVoiceRepository;
    private final AdminVoiceMapper adminVoiceMapper;
    private final ContentService contentService;
    private final BucketMapper bucketMapper;
    private final BucketService bucketService;

    // content (한 줄)단위 관리자 목소리
    public AdminVoiceResDto findAdminVoiceByContentId(Genders gender, int contentId) {
        return adminVoiceMapper.toResDto(
            adminVoiceRepository.findAdminVoiceByContentId(gender, contentId)
                                .orElseThrow(() -> new CustomException(ErrorCode.VOICE_NOT_FOUND)));
    }

    // book (동화책)단위 관리자 목소리 -> 뭔가 쓸일이 있을 거 같아서 일단 만듦
    public List<AdminVoiceResDto> findAdminVoiceByBookId(Genders gender, int bookId) {
        List<AdminVoice> result = adminVoiceRepository.findAdminVoiceByBookId(gender, bookId);
        return adminVoiceMapper.toResDtoList(result);
    }

    public void insertAdminVoice(AdminVoiceInsertDto adminVoiceInsertDto) {
        Content content = contentService.getContentById(adminVoiceInsertDto.getContentId());
        String voiceUrl = bucketService.insertFile(
            bucketMapper.toInsertDto(adminVoiceInsertDto.getFile(), "admin_voice")
        );
        AdminVoice adminVoice = adminVoiceMapper.dtoToEntity(voiceUrl,
            adminVoiceInsertDto.getGender(), content);
        adminVoiceRepository.save(adminVoice);
    }

    public List<AdminFindResDto> findAdminVoiceAndBook(Genders genders, Integer bookId) {
        return adminVoiceRepository.findAdminVoiceAndBook(genders, bookId);
    }
}
