package com.doran.child.repository;

import static com.doran.child.entity.QChild.child;
import static com.doran.parent.entity.QParent.parent;

import java.util.Optional;

import com.doran.child.entity.Child;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChildRepositoryImpl implements ChildRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Child> findChildByParentUserId(int parentUserId) {
        return Optional.ofNullable(jpaQueryFactory
            .select(child)
            .from(parent)
            .join(parent.child, child)
            .where(child.user.id.eq(parentUserId))
            .fetchOne()
        );
    }
}
