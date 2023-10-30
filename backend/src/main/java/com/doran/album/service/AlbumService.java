package com.doran.album.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.album.entity.Album;
import com.doran.album.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public void save(Album album) {
        albumRepository.save(album);
    }

    public List<Album> findAlbumByParentUserId(int parentUserId) {
        return albumRepository.findAlbumByParentUserId(parentUserId);
    }
}
