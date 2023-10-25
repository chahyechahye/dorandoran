package com.doran.page.repository;

import java.util.Optional;

import com.doran.page.entity.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.doran.page.entity.QPage.page;

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
}
