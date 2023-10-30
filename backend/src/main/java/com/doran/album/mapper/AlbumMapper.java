package com.doran.album.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.album.dto.res.AlbumResDto;
import com.doran.album.entity.Album;
import com.doran.child.entity.Child;
import com.doran.parent.entity.Parent;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    @Mapping(source = "parent",target = "parent")
    @Mapping(source = "child", target = "child")
    @Mapping(source = "parent.id", target = "id", ignore = true)
    Album toAlbum(Parent parent, Child child, String imgUrl);

    @Mapping(source = "id", target = "albumId")
    AlbumResDto albumToResDto(Album album);

    List<AlbumResDto> toDtoList(List<Album> albumList);
}
