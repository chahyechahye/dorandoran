package com.doran.album.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.album.entity.Album;
import com.doran.album.repository.AlbumRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public void save(Album album) {
        albumRepository.save(album);
    }

    public Album findAlbumId(int albumId) {
        return albumRepository.findById(albumId)
            .orElseThrow(() -> new CustomException(ErrorCode.ALBUM_NOT_FOUND));
    }

    public List<Album> findAlbumByParentUserId(int parentUserId) {
        return albumRepository.findAlbumByParentUserId(parentUserId);
    }

    public void deleteAlbum(int albumId) {
        findAlbumId(albumId);
        albumRepository.deleteById(albumId);
    }
}
