package com.doran.parent.repository;

import java.util.Optional;

import com.doran.parent.entity.Parent;

public interface ParentRepositoryCustom {
    Optional<Parent> findParentByChildUserId(int childUserId);
    Optional<Parent> findParentByUserId(int userId);
    Optional<Parent> findParentByProfileId(int profileId);
}
