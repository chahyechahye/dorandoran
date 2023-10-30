package com.doran.letter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.dto.res.LetterResDto;
import com.doran.letter.entity.Letter;
import com.doran.letter.mapper.LetterMapper;
import com.doran.letter.repository.LetterRepository;
import com.doran.parent.entity.Parent;
import com.doran.parent.repository.ParentRepository;
import com.doran.parent.service.ParentService;
import com.doran.profile.entity.Profile;
import com.doran.profile.repository.ProfileRepository;
import com.doran.profile.service.ProfileService;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.dto.InsertDto;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.common.UserInfo;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterService{
    private final LetterMapper letterMapper;
    private final LetterRepository letterRepository;
    private final BucketMapper bucketMapper;
    private final BucketService bucketService; // 편지 내용 저장 URL
    // 부모한테 보낸 편지 조회

    // 자식(Profile)한테 보낸 편지 조회

    // 편지 등록
    public void insertLetter(Letter letter){
        letterRepository.save(letter);
    }
}
