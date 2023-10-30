package com.doran.favorite.repository;

import static com.doran.book.entity.QBook.*;
import static com.doran.favorite.entity.QFavorite.*;

import java.util.List;

import com.doran.book.entity.Book;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FavoriteRepositoryCustomImpl implements FavoriteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Book> selectChildFavoriteBook(int profileId) {
        return jpaQueryFactory
            .select(book)
            .from(favorite)
            .join(favorite.book, book)
            .where(favorite.profile.id.eq(profileId))
            .fetch();
    }
}
