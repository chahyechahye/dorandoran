package com.doran.profile.repository;

import static com.doran.profile.entity.QProfile.*;

import java.util.List;
import java.util.Optional;

import com.doran.profile.entity.Profile;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Profile> selectAllProfile(int childId) {

        return jpaQueryFactory
            .select(profile)
            .from(profile)
            .where(profile.child.id.eq(childId))
            .fetch();
    }

    @Override
    public Optional<Profile> selectProfile(int childId, int profileId) {

        return Optional.ofNullable(
            jpaQueryFactory
                .select(profile)
                .from(profile)
                .where(profile.id.eq(profileId), profile.child.id.eq(childId))
                .fetchOne()
        );
    }

}
