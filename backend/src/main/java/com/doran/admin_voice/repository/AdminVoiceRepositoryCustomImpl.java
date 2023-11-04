package com.doran.admin_voice.repository;

import static com.doran.admin_voice.entity.QAdminVoice.*;
import static com.doran.content.entity.QContent.*;
import static com.doran.page.entity.QPage.*;

import java.util.List;
import java.util.Optional;

import com.doran.admin_voice.entity.AdminVoice;
import com.doran.utils.common.Genders;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminVoiceRepositoryCustomImpl implements AdminVoiceRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<AdminVoice> findAdminVoiceByContentId(Genders gender,int contentId){
        return Optional.ofNullable(jpaQueryFactory
            .selectFrom(adminVoice)
            .where(adminVoice.content.id.eq(contentId),
                adminVoice.voiceGender.eq(gender))
            .fetchOne()
        );
    }
    // 책 단위 여성 관리자 목소리 호출
    @Override
    public List<AdminVoice> findAdminVoiceByBookId(Genders gender, int bookId){
        return jpaQueryFactory
            .select(adminVoice)
            .from(adminVoice)
            .leftJoin(content).on(adminVoice.content.eq(content))
            .leftJoin(page).on(content.page.eq(page))
            .where(page.book.id.eq(bookId),
                adminVoice.voiceGender.eq(gender))
            .fetch();
    }
}
