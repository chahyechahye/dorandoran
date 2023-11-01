package com.doran.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.favorite.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>, FavoriteRepositoryCustom {
}
