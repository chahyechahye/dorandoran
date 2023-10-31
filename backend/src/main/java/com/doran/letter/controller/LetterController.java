package com.doran.letter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.dto.res.LetterResDto;
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
    private final ParentService parentService;
    private final LetterService letterService;
    // 편지 조회
    @GetMapping("")
    public ResponseEntity<?> getLetterList(){
        LetterResDto result = null;
        UserInfo userInfo= Auth.getInfo();
        if(!parentService.checkParent(userInfo.getUserRole().getRole())){
            // 부모의 편지 반환
            result = letterService.getParentLetter(userInfo.getUserId());
        }else{
            // 아이의 편지 반환
            result = letterService.getChildLetter(userInfo.getSelectProfileId());
        }
        if(result == null) throw new CustomException(ErrorCode.LETTER_NOT_FOUND);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, result);
    }
    // 편지 등록
    @PostMapping("")
    public ResponseEntity<?> insertLetter(LetterInsertDto letterInsertDto){
        Letter letter = letterService.insertLetter(letterInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }
}
