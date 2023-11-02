package com.doran.admin_voice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.admin_voice.dto.req.AdminVoiceInsertDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDtoList;
import com.doran.admin_voice.entity.AdminVoice;
import com.doran.admin_voice.mapper.AdminVoiceMapper;
import com.doran.admin_voice.repository.AdminVoiceRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminVoiceService {
    private final AdminVoiceRepository adminVoiceRepository;
    private final AdminVoiceMapper adminVoiceMapper;
    // content (한 줄)단위 남성 관리자 목소리
    public AdminVoiceResDto findMaleAdminVoiceByContentId(int contentId){
        return adminVoiceMapper.toResDto(
            adminVoiceRepository.findMaleAdminVoiceByContentId(contentId)
            .orElseThrow(() -> new CustomException(ErrorCode.VOICE_NOT_FOUND)));
    }
    // content (한 줄)단위 여성 관리자 목소리
    public AdminVoiceResDto findFemaleAdminVoiceByContentId(int contentId){
        return adminVoiceMapper.toResDto(
            adminVoiceRepository.findFemaleAdminVoiceByContentId(contentId)
                .orElseThrow(()-> new CustomException(ErrorCode.VOICE_NOT_FOUND)));
    }

    // book (동화책)단위 남성 관리자 목소리
    public AdminVoiceResDtoList findMaleAdminVoiceByBookId(int bookId){
        List<AdminVoice> result = adminVoiceRepository.findMaleAdminVoiceByBookId(bookId);
        List<AdminVoiceResDto> adminVoiceResDtoList = new ArrayList<>();
        for(AdminVoice adminVoice : result){
            adminVoiceResDtoList.add(adminVoiceMapper.toResDto(adminVoice));
        }
        return adminVoiceMapper.toResDtoList(adminVoiceResDtoList, adminVoiceResDtoList.size());
    }
    // book (동화책)단위 여성 관리자 목소리
    public AdminVoiceResDtoList findFemaleAdminVoiceByBookId(int bookId){
        List<AdminVoice> result = adminVoiceRepository.findFemaleAdminVoiceByBookId(bookId);
        List<AdminVoiceResDto> adminVoiceResDtoList = new ArrayList<>();
        for(AdminVoice adminVoice : result){
            adminVoiceResDtoList.add(adminVoiceMapper.toResDto(adminVoice));
        }
        return adminVoiceMapper.toResDtoList(adminVoiceResDtoList, adminVoiceResDtoList.size());
    }

}
