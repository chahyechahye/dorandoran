package com.doran.letter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.dto.res.LetterResDto;
import com.doran.letter.entity.Letter;
import com.doran.parent.entity.Parent;
import com.doran.profile.entity.Profile;

import lombok.RequiredArgsConstructor;

@Mapper(componentModel = "spring")
public interface LetterMapper {
    @Mapping(target = "id", ignore = true)
    Letter insertLettertoLetter(LetterInsertDto letterInsertDto, Parent parent, Profile profile, String contentUrl, int receiverId, int senderId);



    LetterResDto letterToResDto(Letter letter, int unreadCount);


}
