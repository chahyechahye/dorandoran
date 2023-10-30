package com.doran.child.repository;

import java.util.Optional;

import com.doran.child.dto.res.ChildDto;
import com.doran.child.entity.Child;

public interface ChildRepositoryCustom {
    Optional<ChildDto> findChildToParentUserId(int userId);

    Optional<ChildDto> findChildToChildUserId(int userId);
    Optional<Child> findChildEntityByParentUserId(int parentUserId);
    Optional<Child> findChildEntityByChildUserId(int childUserId);
}
