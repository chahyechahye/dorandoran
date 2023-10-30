package com.doran.letter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.doran.utils.bucket.dto.InsertDto;
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
@RequestMapping("/api/letter")
@RequiredArgsConstructor
@Slf4j
public class LetterController {
    private final ParentRepository parentRepository;
    private final ParentService parentService;
    private final ProfileRepository profileRepository;
    private final BucketMapper bucketMapper;
    private final BucketService bucketService;
    private final LetterMapper letterMapper;
    private final LetterService letterService;
    // 편지 조회

    // 편지 등록
    @PostMapping("")
    public ResponseEntity<?> insertLetter(LetterInsertDto letterInsertDto){
        log.info("편지 등록");
        UserInfo userInfo = Auth.getInfo();
        Parent parent;
        Profile profile;
        int senderId;
        if(parentService.checkParent(userInfo.getUserRole().getRole())){
            // 받는 사람이 부모일 경우, 보내는 사람이 아이일 경우
            parent = parentService.findParentByChildUserId(userInfo.getUserId());
            profile = profileRepository.findById(letterInsertDto.getProfileId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
            senderId = profile.getId();
        }else{
            // 받는 사람이 아이일 경우, 보내는 사람이 부모일 경우
            parent = parentRepository.findParentByChildUserId(letterInsertDto.getReceiverId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
            profile = profileRepository.findById(letterInsertDto.getProfileId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
            senderId = parent.getId();
        }
        // 편지 저장 시 파일 이름 Letter_().PNG
        String name = "Letter_" + Auth.getInfo().getUserId() + ".PNG";
        InsertDto insertDto = bucketMapper.toInsertDto(letterInsertDto.getContent(), name);
        String contentUrl = bucketService.insertFile(insertDto);
        Letter letter = letterMapper.insertLettertoLetter(letterInsertDto,parent,profile,senderId,contentUrl);
        letterService.insertLetter(letter);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }
}
