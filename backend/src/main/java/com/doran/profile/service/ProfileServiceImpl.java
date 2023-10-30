package com.doran.profile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.animal.entity.Animal;
import com.doran.animal.mapper.AnimalMapper;
import com.doran.animal.repository.AnimalRepository;
import com.doran.child.repository.ChildRepository;
import com.doran.profile.dto.res.ProfileDto;
import com.doran.profile.dto.res.ProfileListDto;
import com.doran.profile.entity.Profile;
import com.doran.profile.mapper.ProfileMapper;
import com.doran.profile.repository.ProfileRepository;
import com.doran.utils.common.UserInfo;
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
    private final AnimalRepository animalRepository;
    private final ChildRepository childRepository;
    private final ProfileMapper profileMapper;
    private final AnimalMapper animalMapper;

    @Override
    public ProfileListDto selectAllProfile(int childId) {
        List<Profile> profileList = profileRepository.selectAllProfile(childId);
        List<ProfileDto> profileDtoList = new ArrayList<>();

        for (Profile profile : profileList) {
            ProfileDto profileDto = profileMapper.toProfileDto(
                profile.getId(),
                childId,
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
            childId,
            animalMapper.toAnimalDto(
                profile.getAnimal().getId(),
                profile.getAnimal().getName(),
                profile.getAnimal().getImgUrl()),
            profile.getName());
    }

    @Override
    public void createChildProfile(int childId, String name) {
        Profile profile = new Profile();
        profile.setAnimal(
            animalRepository.selectAnimal(1).orElseThrow(() -> new CustomException(ErrorCode.ANIMAL_NOT_FOUND)));
        int userId = childRepository.findChildToParentUserId(childId)
                                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND)).getId();
        profile.setChild(
            childRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.CHILD_NOT_FOUND)));

        profile.setName(name);
        profileRepository.save(profile);
    }

    @Override
    public void updateProfileAnimal(UserInfo userInfo, int animalId) {
        Profile profile = profileRepository.selectProfile(userInfo.getUserId(), userInfo.getSelectProfileId())
                                           .orElseThrow(() -> new CustomException(
                                               ErrorCode.PROFILE_NOT_FOUND));
        Animal animal = animalRepository.selectAnimal(animalId)
                                        .orElseThrow(() -> new CustomException(ErrorCode.ANIMAL_NOT_FOUND));
        profile.setAnimal(animal);
        profileRepository.save(profile);
    }

    @Override
    public ProfileDto selectProfile(int profileId) {

        Profile profile = profileRepository.findById(profileId)
                                           .orElseThrow(() -> new CustomException(ErrorCode.PROFILE_NOT_FOUND));
        return profileMapper.toProfileDto(
            profile.getId(),
            profile.getChild().getId(),
            animalMapper.toAnimalDto(
                profile.getAnimal().getId(),
                profile.getAnimal().getName(),
                profile.getAnimal().getImgUrl()),
            profile.getName());
    }
}
