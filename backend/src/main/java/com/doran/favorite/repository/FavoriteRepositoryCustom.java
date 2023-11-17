package com.doran.favorite.repository;

import java.util.List;
import java.util.Optional;

import com.doran.book.entity.Book;
import com.doran.favorite.entity.Favorite;

public interface FavoriteRepositoryCustom {
    List<Book> selectChildFavoriteBook(int profileId);

    Optional<Favorite> findFavoriteByProfileIdAndBookId(int profileId, int bookId);
}
