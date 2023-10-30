package com.doran.child.repository;

import java.util.Optional;

import com.doran.child.entity.Child;

public interface ChildRepositoryCustom {
    Optional<Child> findChildByParentUserId(int parentUserId);
}
