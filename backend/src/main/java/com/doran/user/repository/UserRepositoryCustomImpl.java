package com.doran.user.repository;

import static com.doran.child.entity.QChild.*;
import static com.doran.parent.entity.QParent.*;
import static com.doran.profile.entity.QProfile.*;
import static com.doran.user.entity.QUser.*;

import java.util.Optional;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.entity.User;
import com.doran.user.type.Roles;
import com.doran.utils.common.UserInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<UserTokenBaseDto> findUser(String email, Provider provider) {
        return Optional.ofNullable(jpaQueryFactory
            .select(Projections.fields(UserTokenBaseDto.class,
                user.id.as("userId"),
                parent.id.as("parentId"),
                parent.provider,
                user.userRole))
            .from(parent)
            .join(parent.user, user)
            .where(parent.email.eq(email),
                parent.provider.eq(provider))
            .fetchOne());
    }

    @Override
    public Optional<UserTokenBaseDto> findUser(int childId, int profileId) {
        return Optional.ofNullable(
            jpaQueryFactory
                .select(Projections.fields(UserTokenBaseDto.class,
                    user.id.as("userId"),
                    child.id.as("childId"),
                    profile.id.as("selectProfileId"),
                    user.userRole))
                .from(profile)
                .join(profile.child, child)
                .where(profile.child.id.eq(childId),
                    profile.id.eq(profileId))
                .fetchOne());
    }

    @Override
    public Optional<UserInfo> findUser(int userId, Roles roles) {
        return Optional.ofNullable(jpaQueryFactory
            .select(Projections.fields(UserInfo.class,
                user.id.as("userId"),
                user.userRole))
            .from(user)
            .where(user.id.eq(userId),
                user.userRole.eq(roles))
            .fetchOne());
    }

    @Override
    public Optional<UserInfo> findUser(int userId, int profileId, Roles roles) {
        return Optional.ofNullable(jpaQueryFactory
            .select(Projections.fields(UserInfo.class,
                user.id.as("userId"),
                profile.id.as("selectProfileId"),
                user.userRole))
            .from(profile)
            .join(profile.child, child)
            .join(child.user, user)
            .where(profile.id.eq(profileId),
                user.id.eq(userId),
                user.userRole.eq(roles))
            .fetchOne());
    }
    @Override
    public Optional<User> findUserByParentId(int parentId){
        return Optional.ofNullable(jpaQueryFactory
            .select(user)
            .from(user)
            .leftJoin(parent).on(user.eq(parent.user))
            .where(parent.id.eq(parentId))
            .fetchOne());
    }
    @Override
    public Optional<User> findUserByProfileId(int profileId){
        return Optional.ofNullable(jpaQueryFactory
            .select(user)
            .from(user)
            .leftJoin(child).on(user.eq(child.user))
            .leftJoin(profile).on(child.eq(profile.child))
            .where(profile.id.eq(profileId))
            .fetchOne());
    }
}
