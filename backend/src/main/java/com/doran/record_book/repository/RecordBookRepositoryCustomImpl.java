package com.doran.record_book.repository;

import static com.doran.record_book.entity.QRecordBook.*;

import java.util.List;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordBookRepositoryCustomImpl implements RecordBookRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Long> findToTalPage() {
        return jpaQueryFactory
            .select(recordBook.count())
            .from(recordBook)
            .where(recordBook.title.in(
                JPAExpressions
                    .select(recordBook.title)
                    .from(recordBook)
                    .groupBy(recordBook.title)
            ))
            .groupBy(recordBook.title)
            .fetch();
    }
}
