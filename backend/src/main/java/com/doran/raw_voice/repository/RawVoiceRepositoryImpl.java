package com.doran.raw_voice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RawVoiceRepositoryImpl implements RawVoiceRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
}
