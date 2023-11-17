package com.doran.album.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doran.album.dto.req.AlbumReqDto;
import com.doran.album.dto.res.AlbumResDto;
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
    public ResponseEntity<?> insertAlbum(AlbumReqDto albumReqDto) {
        log.info("앨범 등록");
        int userId = Auth.getInfo().getUserId();
        String role = Auth.getInfo().getUserRole().getRole();

        if (albumReqDto != null) {
            log.info(albumReqDto.getMultipartFile().getContentType());
        } else
            log.info("응애 널이에용");

        Parent findParent = null;
        Child findChild = null;

        // 부모아이디, 자식 아이디 조회
        if (parentService.checkParent(role)) { // 아이일때
            log.info("아이로 등록");
            findParent = parentService.findParentByChildUserId(userId);
            findChild = childService.findChildEntityByChildUserId(userId);
        } else {
            log.info("부모로 등록");
            findChild = childService.findChildEntityByParentUserId(userId);
            findParent = findChild.getParent();
        }

        String imgUrl = bucketService.insertFile(bucketMapper.toInsertDto(albumReqDto.getMultipartFile(), "album"));
        albumService.save(albumMapper.toAlbum(findParent, findChild, imgUrl));

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }

    //앨범 조회
    @GetMapping("")
    public ResponseEntity<?> getAlbum() {
        log.info("앨범 조회");
        int userId = Auth.getInfo().getUserId();
        String role = Auth.getInfo().getUserRole().getRole();

        int parentUserId = parentService.getParentUserId(userId, role);
        List<Album> albumList = albumService.findAlbumByParentUserId(parentUserId);
        List<AlbumResDto> albumResDtoList = albumMapper.toDtoList(albumList);

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, albumResDtoList);
    }

    //앨범 삭제
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PARENT')")
    @DeleteMapping("/{album_id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable("album_id") int albumId) {
        albumService.deleteAlbum(albumId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }
}
