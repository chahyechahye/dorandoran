package com.doran.record_book.repository;

import static com.doran.record_book.entity.QRecordBook.*;

import java.util.List;
import java.util.Optional;

import com.doran.record_book.dto.res.ScriptDto;
import com.doran.record_book.entity.RecordBook;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordBookRepositoryCustomImpl implements RecordBookRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

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

    @Override
    public Optional<RecordBook> findRecordBookByScript(String title, int scriptNum) {
        return Optional.ofNullable(jpaQueryFactory
            .select(recordBook)
            .from(recordBook)
            .where(recordBook.title.eq(title),
                recordBook.scriptNum.eq(scriptNum))
            .fetchOne());
    }

    @Override
    public void updateRecordBook() {
        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, recordBook);
        long updatedCount = jpaUpdateClause.set(recordBook.script,
                Expressions.stringTemplate(
                    "replace(replace(replace(replace({0}, {1}, {2}), {3}, {4}), {5}, {6}) ,{7}, {8})",
                    recordBook.script,
                    Expressions.constant("“"),
                    Expressions.constant("\""),
                    Expressions.constant("”"),
                    Expressions.constant("\""),
                    Expressions.constant("‘"),
                    Expressions.constant("'"),
                    Expressions.constant("’"),
                    Expressions.constant("'")))
            .execute();
    }
}
