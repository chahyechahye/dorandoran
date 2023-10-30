package com.doran.album.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.doran.album.entity.Album;
import com.doran.album.mapper.AlbumMapper;
import com.doran.album.service.AlbumService;
import com.doran.child.entity.Child;
import com.doran.child.service.ChildService;
import com.doran.parent.entity.Parent;
import com.doran.parent.service.ParentService;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.mapper.BucketMapper;
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
    private final ParentService parentService;
    private final ChildService childService;
    private final BucketMapper bucketMapper;
    private final AlbumMapper albumMapper;

    //앨범 등록
    @PostMapping("")
    public ResponseEntity<?> insertAlbum(MultipartFile multipartFile) {
        UserInfo userInfo = Auth.getInfo();
        Parent findParent = null;
        Child findChild = null;

        // 부모아이디, 자식 아이디 조회
        if (parentService.checkParent(userInfo.getUserRole().getRole())) { // 아이일때
            log.info("아이로 등록");
            findParent = parentService.findParentByChildUserId(userInfo.getUserId());
            findChild = findParent.getChild();
        } else {
            log.info("부모로 등록");
            findChild = childService.findChildEntityByParentUserId(userInfo.getUserId());
            findParent = findChild.getParent();
        }

        String imgUrl = bucketService.insertFile(bucketMapper.toInsertDto(multipartFile, "album"));
        albumService.save(albumMapper.toAlbum(findParent, findChild, imgUrl));

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }

    //앨범 조회

    //앨범 삭제
}
