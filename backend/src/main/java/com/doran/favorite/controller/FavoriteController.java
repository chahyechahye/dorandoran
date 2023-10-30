package com.doran.favorite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doran.book.dto.res.BookListDto;
import com.doran.child.service.ChildService;
import com.doran.favorite.dto.req.FavoriteReqDto;
import com.doran.favorite.service.FavoriteService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.UserInfo;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final ChildService childService;
    private final FavoriteService favoriteService;

    @PreAuthorize("hasRole('ROLE_PARENT')")
    @GetMapping("")
    public ResponseEntity<?> findChildFavoriteBook(@RequestParam int profileId) {

        UserInfo userInfo = Auth.getInfo();

        BookListDto bookListDto = favoriteService.findChildFavoriteBook(profileId);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, bookListDto);
    }

    @PreAuthorize("hasRole('ROLE_CHILD')")
    @PostMapping("")
    public ResponseEntity<?> saveChildFavoriteBook(@RequestBody FavoriteReqDto req) {

        UserInfo userInfo = Auth.getInfo();

        favoriteService.saveChildFavoriteBook(userInfo.getSelectProfileId(), req.getBookId());

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }
}
