package com.doran.raw_voice.repository;

import static com.doran.raw_voice.entity.QRawVoice.*;

import java.util.List;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RawVoiceRepositoryCustomImpl implements RawVoiceRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public List<RawVoiceResDto> findRawVoiceByUserId(int userId) {
        return jpaQueryFactory
            .select(Projections.fields(RawVoiceResDto.class,
                rawVoice.id.as("rvId"),
                rawVoice.voiceUrl.as("voiceUrl"),
                rawVoice.voiceGender.as("gender")
            ))
            .from(rawVoice)
            .where(rawVoice.user.id.eq(userId))
            .fetch();
    }
}
