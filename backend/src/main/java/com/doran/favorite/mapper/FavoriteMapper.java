package com.doran.favorite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.book.entity.Book;
import com.doran.favorite.entity.Favorite;
import com.doran.profile.entity.Profile;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    @Mapping(target = "id", ignore = true)
    Favorite toFavorite(Book book, Profile profile);
}
