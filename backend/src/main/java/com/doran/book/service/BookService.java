package com.doran.book.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.book.mapper.BookMapper;
import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.dto.res.BookListDto;
import com.doran.book.dto.res.BookResDto;
import com.doran.book.entity.Book;
import com.doran.book.repository.BookRepository;
import com.doran.exception.dto.CustomException;
import com.doran.exception.dto.ErrorCode;
import com.doran.utils.bucket.dto.InsertDto;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BucketService bucketService;
    private final BucketMapper bucketMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    //책 예외 체킹
    public Book findBookById(int bookId) {
        return bookRepository.findById(bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
    }

    // 책 등록
    public void insertBook(BookInsertDto bookInsertDto) throws IOException {
        //버킷 업로드
        InsertDto insertDto = bucketMapper.bookInsertToBucket(bookInsertDto);
        String imgUrl = bucketService.insertFile(insertDto);

        // DB 업로드
        bookRepository.save(bookMapper.bookInsertToBook(bookInsertDto, imgUrl));
    }

    //책 조회
    public BookListDto getBookList() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResDto> bookResDtoList = bookMapper.toDtoList(bookList);

        return new BookListDto(bookResDtoList.size(), bookResDtoList);
    }
}
