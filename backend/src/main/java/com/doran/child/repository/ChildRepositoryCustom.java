package com.doran.child.repository;

import java.util.Optional;

import com.doran.child.dto.res.ChildDto;

public interface ChildRepositoryCustom {
    Optional<ChildDto> findChildToParentUserId(int userId);

    Optional<ChildDto> findChildToChildUserId(int userId);
}
