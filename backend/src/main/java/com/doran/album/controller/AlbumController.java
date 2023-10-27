package com.doran.album.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.doran.album.service.AlbumService;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.common.UserInfo;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/album")
public class AlbumController {
    private final AlbumService albumService;
    private final BucketService bucketService;

    //앨범 등록
    @PostMapping("/")
    //@PreAuthorize()
    public ResponseEntity<?> insertAlbum(MultipartFile multipartFile) {
        UserInfo userInfo = Auth.getInfo();
        // 버킷 저장

        // 부모아이디, 자식 아이디 조회

        // db 저장

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }

    //앨범 조회

    //앨범 삭제
}
