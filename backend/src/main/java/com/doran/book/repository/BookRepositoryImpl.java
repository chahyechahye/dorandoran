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
        //헷갈림 주의!!!!!!
        // db의 imgUrl 컬럼 = characterUrl
        // db의 imgUrl2 컬럼 = imgUrl
        return jpaQueryFactory
            .select(Projections.fields(BookResDto.class,
                book.id.as("bookId"),
                book.title.as("title"),
                book.imgUrl.as("characterUrl"),
                book.author.as("author"),
                book.publisher.as("publisher"),
                book.imgUrl2.as("imgUrl"),
                page.count().as("totalPageCnt")
            ))
            .from(page)
            .groupBy(page.book)
            .join(page.book, book)
            .fetch();
    }
}
