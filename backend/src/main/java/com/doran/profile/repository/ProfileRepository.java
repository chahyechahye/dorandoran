package com.doran.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.profile.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>, ProfileRepositoryCustom {
}
