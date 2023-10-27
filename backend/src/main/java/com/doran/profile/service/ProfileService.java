package com.doran.profile.service;

import com.doran.profile.dto.res.ProfileDto;
import com.doran.profile.dto.res.ProfileListDto;

public interface ProfileService {

    ProfileListDto selectAllProfile(int childId);

    ProfileDto selectProfile(int childId, int profileId);

    void createChildProfile(int childId);

    void updateProfileAnimal(int animalId);
}
