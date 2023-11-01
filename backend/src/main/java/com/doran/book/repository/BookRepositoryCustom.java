package com.doran.book.repository;

import java.util.List;

import com.doran.book.dto.res.BookResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

public interface BookRepositoryCustom {
    List<BookResDto> findBookWithPageCnt();
}
