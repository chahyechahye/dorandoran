package com.doran.letter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.entity.Letter;
import com.doran.letter.mapper.LetterMapper;
import com.doran.letter.service.LetterService;
import com.doran.parent.entity.Parent;
import com.doran.parent.repository.ParentRepository;
import com.doran.parent.service.ParentService;
import com.doran.profile.entity.Profile;
import com.doran.profile.repository.ProfileRepository;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.common.UserInfo;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/letter")
@RequiredArgsConstructor
public class LetterController {
    private final ParentService parentService; // 부모 아이 판별을 위해 import
    private final ParentRepository parentRepository;
    private final ProfileRepository profileRepository;
    private final BucketMapper bucketMapper;
    private final BucketService bucketService;
    private final LetterMapper letterMapper;
    private final LetterService letterService;
    // 편지 조회


    // 편지 등록
    public ResponseEntity<?> insertLetter(LetterInsertDto letterInsertDto){
        UserInfo userInfo = Auth.getInfo();
        Parent parent = null;
        Profile profile = null;
        if(parentService.checkParent(userInfo.getUserRole().getRole())){
            // 보내는 사람 프로필(아이), 받는 사람 부모일 때
            parent = parentRepository.findParentByProfileId(letterInsertDto.getProfileId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
            profile = profileRepository.findById(letterInsertDto.getProfileId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        }else{
            // 보내는 사람 부모, 받는 사람 프로필(아이)일 때
            parent = parentRepository.findParentByUserId(userInfo.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
            profile = profileRepository.findById(letterInsertDto.getProfileId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        }
        String contentUrl = bucketService.insertFile(bucketMapper.toInsertDto(letterInsertDto.getContent(), "letter"));
        Letter letter = letterMapper.insertLettertoLetter(letterInsertDto,parent,profile,contentUrl);
        letterService.insertLetter(letter);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }
}
