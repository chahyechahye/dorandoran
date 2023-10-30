package com.doran.letter.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LetterRepositoryImpl implements LetterRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
}
