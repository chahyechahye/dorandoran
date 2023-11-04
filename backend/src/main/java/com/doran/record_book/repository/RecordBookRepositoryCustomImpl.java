package com.doran.record_book.repository;

import static com.doran.record_book.entity.QRecordBook.*;

import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordBookRepositoryCustomImpl implements RecordBookRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tuple> findToTalPage() {
        return jpaQueryFactory
            .select(recordBook.count(),
                recordBook.title)
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
