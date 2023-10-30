package com.doran.child.repository;

import static com.doran.child.entity.QChild.*;
import static com.doran.parent.entity.QParent.*;
import static com.doran.user.entity.QUser.*;

import java.util.Optional;

import com.doran.child.dto.res.ChildDto;
import com.doran.child.entity.Child;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChildRepositoryCustomImpl implements ChildRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Child> findChildEntityByParentUserId(int parentUserId) {
        return Optional.ofNullable(jpaQueryFactory
            .select(child)
            .from(child)
            .leftJoin(child.parent, parent)
            .leftJoin(parent.user, user)
            .where(user.id.eq(parentUserId))
            .fetchOne()
        );
    }


    @Override
    public Optional<ChildDto> findChildToParentUserId(int userId) {
        return Optional.ofNullable(
            jpaQueryFactory
                .select(Projections.fields(
                    ChildDto.class,
                    child.id
                ))
                .from(child)
                .join(child.parent, parent)
                .join(parent.user, user)
                .where(user.id.eq(userId))
                .fetchOne()
        );
    }

    @Override
    public Optional<Child> findChildEntityByChildUserId(int childUserId) {
        return Optional.ofNullable(jpaQueryFactory
            .select(child)
            .from(child)
            .join(child.user, user)
            .where(user.id.eq(childUserId))
            .fetchOne());
    }


    @Override
    public Optional<ChildDto> findChildToChildUserId(int userId) {
        return Optional.ofNullable(
            jpaQueryFactory
                .select(Projections.fields(
                    ChildDto.class,
                    child.id
                ))
                .from(child)
                .join(child.user, user)
                .where(user.id.eq(userId))
                .fetchOne()
        );
    }
}
