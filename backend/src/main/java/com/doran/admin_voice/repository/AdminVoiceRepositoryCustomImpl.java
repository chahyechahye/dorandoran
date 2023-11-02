package com.doran.admin_voice.repository;

import static com.doran.admin_voice.entity.QAdminVoice.*;
import static com.doran.content.entity.QContent.*;
import static com.doran.page.entity.QPage.*;

import java.util.List;
import java.util.Optional;

import com.doran.admin_voice.entity.AdminVoice;
import com.doran.admin_voice.type.Genders;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminVoiceRepositoryCustomImpl implements AdminVoiceRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<AdminVoice> findMaleAdminVoiceByContentId(int contentId){
        return Optional.ofNullable(jpaQueryFactory
            .selectFrom(adminVoice)
            .where(adminVoice.content.id.eq(contentId),
                adminVoice.gender.eq(Genders.MALE))
            .fetchOne()
        );
    }
    @Override
    public Optional<AdminVoice> findFemaleAdminVoiceByContentId(int contentId){
        return Optional.ofNullable(jpaQueryFactory
            .selectFrom(adminVoice)
            .where(adminVoice.content.id.eq(contentId),
                adminVoice.gender.eq(Genders.FEMALE))
            .fetchOne()
        );
    }
    @Override
    public List<AdminVoice> findMaleAdminVoiceByBookId(int bookId){
        return jpaQueryFactory
            .select(adminVoice)
            .from(adminVoice)
            .leftJoin(content).on(adminVoice.content.eq(content))
            .leftJoin(page).on(content.page.eq(page))
            .where(page.book.id.eq(bookId),
                adminVoice.gender.eq(Genders.MALE))
            .fetch();
    }
    // 책 단위 여성 관리자 목소리 호출
    @Override
    public List<AdminVoice> findFemaleAdminVoiceByBookId(int bookId){
        return jpaQueryFactory
            .select(adminVoice)
            .from(adminVoice)
            .leftJoin(content).on(adminVoice.content.eq(content))
            .leftJoin(page).on(content.page.eq(page))
            .where(page.book.id.eq(bookId),
                adminVoice.gender.eq(Genders.FEMALE))
            .fetch();
    }
}
