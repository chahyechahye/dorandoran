package com.doran.letter.service;

import org.springframework.stereotype.Service;

import com.doran.letter.repository.LetterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LetterService{
    private final LetterRepository letterRepository;
    // 부모한테 보낸 편지 조회

    // 자식(Profile)한테 보낸 편지 조회

    // 편지 등록
}
