package com.doran.favorite.service;

import com.doran.book.dto.res.BookListDto;

public interface FavoriteService {
    BookListDto findChildFavoriteBook(int profileId);

    void saveChildFavoriteBook(int profileId, int bookId);
}
