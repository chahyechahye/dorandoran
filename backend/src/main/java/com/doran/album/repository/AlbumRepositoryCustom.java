package com.doran.album.repository;

import java.util.List;

import com.doran.album.entity.Album;

public interface AlbumRepositoryCustom {
    List<Album> findAlbumByParentUserId(int parentUserId);
}
