package com.doran.letter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.dto.res.LetterResDto;
import com.doran.letter.dto.res.LetterResDtoList;
import com.doran.letter.dto.res.UnreadLetterResDto;
import com.doran.letter.entity.Letter;
import com.doran.parent.entity.Parent;
import com.doran.profile.entity.Profile;

import lombok.RequiredArgsConstructor;

@Mapper(componentModel = "spring")
public interface LetterMapper {
    @Mapping(target = "id", ignore = true)
    Letter insertLettertoLetter(LetterInsertDto letterInsertDto, Parent parent, Profile profile, String contentUrl, int receiverId, int senderId);

    UnreadLetterResDto letterToUnreadResDto(Integer size);


    @Mapping(source="id", target="letterId")
    LetterResDto letterToResDto(Letter letter);
    List<LetterResDto> letterListToResDtoList(List<Letter> letterList);
    LetterResDtoList letterListToResDtoList(List<LetterResDto> letterResDtoList, Integer size);


}
