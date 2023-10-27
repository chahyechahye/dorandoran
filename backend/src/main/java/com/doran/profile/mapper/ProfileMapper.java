package com.doran.profile.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.animal.dto.res.AnimalDto;
import com.doran.profile.dto.res.ProfileDto;
import com.doran.profile.dto.res.ProfileListDto;

@Mapper(componentModel = "Spring")
public interface ProfileMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    ProfileDto toProfileDto(int id, AnimalDto animal, String name);

    default ProfileListDto toProfileListDto(List<ProfileDto> profileListDto) {
        ProfileListDto temp = new ProfileListDto();
        temp.setProfileList(profileListDto);
        return temp;
    }

}
