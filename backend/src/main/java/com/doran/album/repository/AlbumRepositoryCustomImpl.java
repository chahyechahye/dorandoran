package com.doran.album.repository;

import java.util.List;

import com.doran.album.entity.Album;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.doran.album.entity.QAlbum.album;
import static com.doran.parent.entity.QParent.parent;
import static com.doran.user.entity.QUser.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlbumRepositoryCustomImpl implements AlbumRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Album> findAlbumByParentUserId(int parentUserId) {
        return jpaQueryFactory.select(album)
            .from(album)
            .join(album.parent, parent)
            .join(parent.user, user)
            .where(user.id.eq(parentUserId))
            .fetch();
    }
}
