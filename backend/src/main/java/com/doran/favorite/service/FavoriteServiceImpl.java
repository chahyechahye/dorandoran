package com.doran.favorite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.book.dto.res.BookListDto;
import com.doran.book.dto.res.BookResDto;
import com.doran.book.entity.Book;
import com.doran.book.mapper.BookMapper;
import com.doran.book.repository.BookRepository;
import com.doran.favorite.entity.Favorite;
import com.doran.favorite.repository.FavoriteRepository;
import com.doran.profile.entity.Profile;
import com.doran.profile.repository.ProfileRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final BookRepository bookRepository;
    private final ProfileRepository profileRepository;
    private final BookMapper bookMapper;

    @Override
    public BookListDto findChildFavoriteBook(int profileId) {

        List<Book> bookList = favoriteRepository.selectChildFavoriteBook(profileId);
        int size = bookList.size();
        List<BookResDto> bookResDtoList = new ArrayList<>();
        for (Book book : bookList) {
            bookResDtoList.add(bookMapper.bookToResDto(book));
        }
        log.info("책 : {}", bookResDtoList);
        return new BookListDto(size, bookResDtoList);

    }

    @Override
    public void saveChildFavoriteBook(int profileId, int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        Profile profile = profileRepository.findById(profileId)
                                           .orElseThrow(() -> new CustomException(ErrorCode.PROFILE_NOT_FOUND));

        Favorite favorite = new Favorite();
        favorite.setBook(book);
        favorite.setProfile(profile);

        favoriteRepository.save(favorite);
    }
}
