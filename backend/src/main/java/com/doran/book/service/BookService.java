package com.doran.book.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.doran.book.BookMapper;
import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.entity.Book;
import com.doran.book.repository.BookRepository;
import com.doran.utils.bucket.dto.InsertDto;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BucketService bucketService;
    private final BucketMapper bucketMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public void insertBook(BookInsertDto bookInsertDto) throws IOException {
        //버킷 업로드
        InsertDto insertDto = bucketMapper.bookInsertToBucket(bookInsertDto);
        String imgUrl = bucketService.insertFile(insertDto);

        // DB 업로드
        bookRepository.save(bookMapper.bookInsertToBook(bookInsertDto, imgUrl));
    }
}
