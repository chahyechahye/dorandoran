package com.doran.content.repository;

import java.util.List;

import com.doran.content.dto.res.ContentResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.doran.content.entity.QContent.content;
import static com.doran.processed_voice.entity.QProcessedVoice.processedVoice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContentRepositoryImpl implements ContentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    //해당 페이지의 스크립트와 목소리 반환
    @Override
    public List<ContentResDto> getContentWithVoice(int userId, int pageId) {
        return jpaQueryFactory
            .select(Projections.fields(ContentResDto.class,
                content.id.as("content_id"),
                content.script.as("script"),
                processedVoice.voiceUrl.as("voiceUrl")
                ))
            .from(content)
            .leftJoin(content.processedVoice, processedVoice)
            .on(processedVoice.user.id.eq(userId))
            .where(content.page.id.eq(pageId))
            .fetch();
    }
}
