package com.doran.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.album.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
