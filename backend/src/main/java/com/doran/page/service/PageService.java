package com.doran.page.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.book.entity.Book;
import com.doran.book.service.BookService;
import com.doran.content.dto.res.ContentResDto;
import com.doran.content.service.ContentService;
import com.doran.page.dto.req.PageFindDto;
import com.doran.page.dto.req.PageInsertDto;
import com.doran.page.dto.res.PageDetailDto;
import com.doran.page.dto.res.PageListDto;
import com.doran.page.entity.Page;
import com.doran.page.mapper.PageMapper;
import com.doran.page.repository.PageRepository;
import com.doran.utils.bucket.dto.InsertDto;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.common.Genders;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageService {
    private final BookService bookService;
    private final PageMapper pageMapper;
    private final PageRepository pageRepository;
    private final BucketService bucketService;
    private final ContentService contentService;

    //파일 이름 어떻게 저장 될지 협의 필요함. idx와 bookId를 조합해서 이미지의 이름을 만들면 좋을거같음. 해당 메소드는 이를 위해 만들어놓은 메소드
    //일단 요로코롬 만들고 DB에는 uuid가 저장되게끔 구현함
    public InsertDto convertInsertDto(PageInsertDto pageInsertDto) {
        return new InsertDto(pageInsertDto.getMultipartFile(), String.valueOf(pageInsertDto.getIdx()));
    }

    //동일 페이지에 이미지 업로드시 덮어씌워야함.
    public void insertPage(int bookId, PageInsertDto pageInsertDto) {
        Book book = bookService.findBookById(bookId);
        String imgUrl = bucketService.insertFile(convertInsertDto(pageInsertDto));

        Page page = pageMapper.pageInsertToPage(book, imgUrl, pageInsertDto.getIdx());
        pageRepository.save(page);
    }

    public Page insertPage(int bookId, String imgUrl, int idx) {
        Book book = bookService.findBookById(bookId);

        Page page = pageMapper.pageInsertToPage(book, imgUrl, idx);
        return pageRepository.save(page);
    }

    public List<Page> findPageByBookId(int bookId) {
        bookService.findBookById(bookId);

        return pageRepository.findPagesByBookId(bookId);
    }

    public List<PageDetailDto> getPageAll(int userId, int bookId, Genders genders) {
        return pageRepository.findPageDetailByUserIdAndBookId(userId,bookId, genders);
    }

    public PageListDto findPageByBookIdWithSize(int bookId) {
        bookService.findBookById(bookId);

        List<Page> pageList = pageRepository.findPagesByBookId(bookId);
        return new PageListDto(pageList.size(), pageMapper.toDtoList(pageList));
    }

    public Page findPageIdByIdxAndBookId(int bookId, int idx) {
        log.info("page 조회 서비스 호출");
        return pageRepository.findPageByBookIdAndIdx(bookId, idx)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
    }
}
