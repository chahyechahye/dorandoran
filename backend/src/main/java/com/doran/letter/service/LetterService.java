package com.doran.letter.service;

import org.springframework.stereotype.Service;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.entity.Letter;
import com.doran.letter.repository.LetterRepository;
import com.doran.parent.service.ParentService;
import com.doran.profile.entity.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterService{
    private final LetterRepository letterRepository;
    // 부모한테 보낸 편지 조회

    // 자식(Profile)한테 보낸 편지 조회

    // 편지 등록
    public void insertLetter(Letter letter){
        // 편지 저장 시 파일 이름
        letterRepository.save(letter);
    }
}
