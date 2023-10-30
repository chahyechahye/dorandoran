package com.doran.album.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.album.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer>, AlbumRepositoryCustom {
}
