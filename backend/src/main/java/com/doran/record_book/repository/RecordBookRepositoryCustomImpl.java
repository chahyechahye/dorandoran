package com.doran.record_book.repository;

import static com.doran.record_book.entity.QRecordBook.*;

import java.util.List;

import com.doran.record_book.dto.res.ScriptDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordBookRepositoryCustomImpl implements RecordBookRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Long> findToTalPage(List<String> bookName) {
        return jpaQueryFactory
            .select(recordBook.count())
            .from(recordBook)
            .where(recordBook.title.in(bookName))
            .groupBy(recordBook.title)
            .fetch();
    }

    @Override
    public List<String> findBookName() {
        return jpaQueryFactory
            .select(recordBook.title)
            .from(recordBook)
            .groupBy(recordBook.title)
            .fetch();
    }

    @Override
    public List<ScriptDto> findScript(String bookName) {
        return jpaQueryFactory
            .select(Projections.fields(ScriptDto.class,
                recordBook.script,
                recordBook.scriptNum))
            .from(recordBook)
            .where(recordBook.title.eq(bookName))
            .fetch();
    }
}
