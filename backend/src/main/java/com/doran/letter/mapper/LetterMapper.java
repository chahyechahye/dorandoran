package com.doran.letter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.entity.Letter;
import com.doran.parent.entity.Parent;
import com.doran.profile.entity.Profile;

import lombok.RequiredArgsConstructor;

@Mapper(componentModel = "spring")
public interface LetterMapper {
    @Mapping(source="parent.id", target = "id", ignore = true)
    Letter insertLettertoLetter(LetterInsertDto letterInsertDto, Parent parent, Profile profile, String contentUrl);


}
