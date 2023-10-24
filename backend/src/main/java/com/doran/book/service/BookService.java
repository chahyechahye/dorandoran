package com.doran.book.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.book.BookMapper;
import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.dto.res.BookListDto;
import com.doran.book.dto.res.BookResDto;
import com.doran.book.entity.Book;
import com.doran.book.repository.BookRepository;
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
        log.info(bookList.get(0).getTitle());
        List<BookResDto> bookResDtoList = bookMapper.toDtoList(bookList);

        return new BookListDto(bookResDtoList.size(), bookResDtoList);
    }
}
