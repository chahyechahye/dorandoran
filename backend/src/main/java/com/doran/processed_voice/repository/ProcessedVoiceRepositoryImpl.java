package com.doran.processed_voice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessedVoiceRepositoryImpl implements ProcessedVoiceRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

}
