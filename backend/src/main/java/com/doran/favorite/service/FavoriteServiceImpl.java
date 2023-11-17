package com.doran.favorite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.book.dto.res.BookListDto;
import com.doran.book.dto.res.BookResDto;
import com.doran.book.entity.Book;
import com.doran.book.mapper.BookMapper;
import com.doran.book.repository.BookRepository;
import com.doran.book.service.BookService;
import com.doran.favorite.entity.Favorite;
import com.doran.favorite.mapper.FavoriteMapper;
import com.doran.favorite.repository.FavoriteRepository;
import com.doran.profile.entity.Profile;
import com.doran.profile.repository.ProfileRepository;
import com.doran.profile.service.ProfileService;

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
    private final BookService bookService;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;
    private final BookMapper bookMapper;
    private final FavoriteMapper favoriteMapper;

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
        Optional<Favorite> findFavorite = favoriteRepository.findFavoriteByProfileIdAndBookId(profileId,
            bookId);

        findFavorite.ifPresentOrElse(
            favorite -> {
            },
            () -> {
                Book findBook = bookService.findBookById(bookId);
                Profile findProfile = profileService.findProfileById(profileId);
                Favorite favorite = favoriteMapper.toFavorite(findBook, findProfile);

                favoriteRepository.save(favorite);
            }
        );
    }
}
