package com.doran.admin_voice.repository;

import static com.doran.admin_voice.entity.QAdminVoice.*;
import static com.doran.content.entity.QContent.*;
import static com.doran.page.entity.QPage.*;
import static com.doran.book.entity.QBook.book;
import static com.querydsl.core.group.GroupBy.*;

import java.util.List;
import java.util.Optional;

import com.doran.admin_voice.dto.res.AdminFindResDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.admin_voice.entity.AdminVoice;
import com.doran.book.entity.QBook;
import com.doran.utils.common.Genders;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminVoiceRepositoryCustomImpl implements AdminVoiceRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<AdminVoice> findAdminVoiceByContentId(Genders gender, int contentId) {
        return Optional.ofNullable(jpaQueryFactory
            .selectFrom(adminVoice)
            .where(adminVoice.content.id.eq(contentId),
                adminVoice.voiceGender.eq(gender))
            .fetchOne()
        );
    }

    // 책 단위 여성 관리자 목소리 호출
    @Override
    public List<AdminVoice> findAdminVoiceByBookId(Genders gender, int bookId) {
        return jpaQueryFactory
            .select(adminVoice)
            .from(adminVoice)
            .leftJoin(content).on(adminVoice.content.eq(content))
            .leftJoin(page).on(content.page.eq(page))
            .where(page.book.id.eq(bookId),
                adminVoice.voiceGender.eq(gender))
            .fetch();
    }

    @Override
    public List<AdminFindResDto> findAdminVoiceAndBook() {
        return jpaQueryFactory
            .from(adminVoice)
            .rightJoin(adminVoice.content, content)
            .rightJoin(content.page, page)
            .rightJoin(page.book, book)
            .transform(groupBy(book.id).list(
                Projections.fields(AdminFindResDto.class,
                    book.id.as("bookId"),
                    list(
                        Projections.fields(AdminVoiceResDto.class,
                            content.id.as("contentId"),
                            adminVoice.voiceUrl.as("voiceUrl"))
                    ).as("adminVoiceList"))
            ));
    }
}
