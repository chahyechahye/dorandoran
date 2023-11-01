package com.doran.favorite.repository;

import java.util.List;

import com.doran.book.entity.Book;

public interface FavoriteRepositoryCustom {
    List<Book> selectChildFavoriteBook(int profileId);
}
