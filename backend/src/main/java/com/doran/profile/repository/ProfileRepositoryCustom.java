package com.doran.profile.repository;

import java.util.List;
import java.util.Optional;

import com.doran.profile.entity.Profile;

public interface ProfileRepositoryCustom {

    List<Profile> selectAllProfile(int childId);

    Optional<Profile> selectProfile(int childId, int profileId);

}
