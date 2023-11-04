package com.doran.raw_voice.repository;

import java.util.List;

import com.doran.raw_voice.entity.RawVoice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.doran.raw_voice.entity.QRawVoice.rawVoice;

@RequiredArgsConstructor
public class RawVoiceRepositoryImpl implements RawVoiceRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RawVoice> findRawVoiceByUserId(int userId) {
        return jpaQueryFactory
            .select(rawVoice)
            .from(rawVoice)
            .where(rawVoice.user.id.eq(userId))
            .fetch();
    }
}
