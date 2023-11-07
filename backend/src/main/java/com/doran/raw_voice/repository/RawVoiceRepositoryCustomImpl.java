package com.doran.raw_voice.repository;

import static com.doran.content.entity.QContent.*;
import static com.doran.raw_voice.entity.QRawVoice.*;

import java.util.List;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import com.doran.utils.common.Genders;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RawVoiceRepositoryCustomImpl implements RawVoiceRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public List<RawVoiceResDto> findRawVoiceByUserId(int userId, Genders genders) {
        return jpaQueryFactory
            .select(Projections.fields(RawVoiceResDto.class,
                rawVoice.id.as("rvId"),
                rawVoice.voiceUrl.as("voiceUrl"),
                rawVoice.voiceGender.as("gender")
            ))
            .from(rawVoice)
            .where(rawVoice.user.id.eq(userId))
            .where(genderEq(genders))
            .fetch();
    }

    private static BooleanExpression genderEq(Genders genders) {
        return genders != null ? rawVoice.voiceGender.eq(genders) : null;
    }

}
