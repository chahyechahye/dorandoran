package com.doran.page.repository;

import java.util.List;
import java.util.Optional;

import static com.doran.content.entity.QContent.content;

import com.doran.content.dto.res.ContentResDto;
import com.doran.page.dto.res.PageDetailDto;
import com.doran.page.entity.Page;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.doran.page.entity.QPage.page;
import static com.doran.processed_voice.entity.QProcessedVoice.*;
import static com.querydsl.core.group.GroupBy.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PageRepositoryImpl implements PageRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    //bookId, idx가 일치하는 Page 객체 반환
    @Override
    public Optional<Page> findPageByBookIdAndIdx(int bookId, int idx) {
        return Optional.ofNullable(jpaQueryFactory
            .selectFrom(page)
            .where(page.book.id.eq(bookId),
                page.idx.eq(idx))
            .fetchOne());
    }

    @Override
    public List<PageDetailDto> findPageDetailByUserIdAndBookId(int userId, int bookId) {
        return jpaQueryFactory
            .from(content)
            .join(content.page, page)
            .leftJoin(content.processedVoice, processedVoice)
            .on(processedVoice.user.id.eq(userId))
            .where(page.book.id.eq(bookId))
            .transform(groupBy(page.id).list(
                Projections.fields(
                    PageDetailDto.class,
                    page.id.as("pageId"),
                    page.idx.as("idx"),
                    page.imgUrl.as("imgUrl"),
                    list(
                        Projections.fields(ContentResDto.class,
                            processedVoice.id.as("pv_id"),
                            content.id.as("content_id"),
                            content.script.as("script"),
                            processedVoice.voiceUrl.as("voiceUrl")
                        )
                    ).as("contentResDto")
                )
            ));
    }
}
