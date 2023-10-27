package com.doran.parent.repository;

import java.util.Optional;

import com.doran.parent.entity.Parent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.doran.parent.entity.QParent.parent;
import static com.doran.child.entity.QChild.child;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParentRepositoryImpl implements ParentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Parent> findParentByChildUserId(int childUserId) {
        return Optional.ofNullable(jpaQueryFactory
            .select(parent)
            .from(child)
                .join(child.parent, parent)
                .where(child.user.id.eq(childUserId))
            .fetchOne()
        );
    }
}
