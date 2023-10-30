package com.doran.album.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.album.entity.Album;
import com.doran.child.entity.Child;
import com.doran.parent.entity.Parent;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    @Mapping(source = "parent",target = "parent")
    @Mapping(source = "child", target = "child")
    @Mapping(source = "parent.id", target = "id", ignore = true)
    Album toAlbum(Parent parent, Child child, String imgUrl);
}
