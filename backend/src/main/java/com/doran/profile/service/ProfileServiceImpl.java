package com.doran.profile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.animal.mapper.AnimalMapper;
import com.doran.profile.dto.res.ProfileDto;
import com.doran.profile.dto.res.ProfileListDto;
import com.doran.profile.entity.Profile;
import com.doran.profile.mapper.ProfileMapper;
import com.doran.profile.repository.ProfileRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final AnimalMapper animalMapper;

    @Override
    public ProfileListDto selectAllProfile(int childId) {
        List<Profile> profileList = profileRepository.selectAllProfile(childId);
        List<ProfileDto> profileDtoList = new ArrayList<>();

        for (Profile profile : profileList) {
            ProfileDto profileDto = profileMapper.toProfileDto(
                profile.getId(),
                animalMapper.toAnimalDto(
                    profile.getAnimal().getId(),
                    profile.getAnimal().getName(),
                    profile.getAnimal().getImgUrl()),
                profile.getName());
            profileDtoList.add(profileDto);
        }

        return profileMapper.toProfileListDto(profileDtoList);
    }

    @Override
    public ProfileDto selectProfile(int childId, int profileId) {
        Profile profile = profileRepository.selectProfile(childId, profileId).orElseThrow(() -> new CustomException(
            ErrorCode.PROFILE_NOT_FOUND));

        return profileMapper.toProfileDto(
            profile.getId(),
            animalMapper.toAnimalDto(
                profile.getAnimal().getId(),
                profile.getAnimal().getName(),
                profile.getAnimal().getImgUrl()),
            profile.getName());
    }

    @Override
    public void createChildProfile(int childId) {

    }

    @Override
    public void updateProfileAnimal(int animalId) {

    }
}
