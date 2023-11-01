package com.doran.book.repository;

import static com.doran.book.entity.QBook.book;
import static com.doran.page.entity.QPage.*;

import java.util.List;

import com.doran.book.dto.res.BookResDto;
import com.doran.page.entity.QPage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BookResDto> findBookWithPageCnt() {
        return jpaQueryFactory
            .select(Projections.fields(BookResDto.class,
                book.id.as("bookId"),
                book.title.as("title"),
                book.imgUrl.as("imgUrl"),
                book.author.as("author"),
                book.publisher.as("publisher"),
                page.count().as("totalPageCnt")
            ))
            .from(page)
            .groupBy(page.book)
            .join(page.book, book)
            .fetch();
    }
}
