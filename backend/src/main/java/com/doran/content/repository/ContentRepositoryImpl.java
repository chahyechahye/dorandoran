package com.doran.content.repository;

import static com.doran.content.entity.QContent.*;
import static com.doran.processed_voice.entity.QProcessedVoice.*;

import java.util.List;

import com.doran.content.dto.res.ContentResDto;
import com.doran.page.entity.Page;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContentRepositoryImpl implements ContentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    //해당 페이지의 스크립트와 목소리 반환
    @Override
    public List<ContentResDto> getContentWithVoice(int userId, Integer pageId, Integer bookId) {
        return jpaQueryFactory
            .select(Projections.fields(ContentResDto.class,
                content.id.as("content_id"),
                content.script.as("script"),
                processedVoice.voiceUrl.as("voiceUrl")
            ))
            .from(content)
            .leftJoin(content.processedVoice, processedVoice)
            .on(processedVoice.user.id.eq(userId))
            .where(pageIdEq(pageId))
            .where(bookIdEq(bookId))
            .fetch();
    }

    @Override
    public List<String> findContentByPageList(List<Page> pageList) {
        return jpaQueryFactory
            .select(content.script)
            .from(content)
            .where(content.page.in(pageList))
            .fetch();
    }

    private static BooleanExpression pageIdEq(Integer id) {
        return id != null ? content.page.id.eq(id) : null;
    }

    private static BooleanExpression bookIdEq(Integer id) {
        return id != null ? content.page.book.id.eq(id) : null;
    }

}
