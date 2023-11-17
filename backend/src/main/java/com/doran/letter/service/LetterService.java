package com.doran.letter.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.child.entity.Child;
import com.doran.child.service.ChildService;
import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.dto.res.LetterResDto;
import com.doran.letter.dto.res.LetterResDtoList;
import com.doran.letter.dto.res.UnreadLetterResDto;
import com.doran.letter.entity.Letter;
import com.doran.letter.mapper.LetterMapper;
import com.doran.letter.repository.LetterRepository;
import com.doran.parent.entity.Parent;
import com.doran.parent.service.ParentService;
import com.doran.profile.entity.Profile;
import com.doran.profile.repository.ProfileRepository;
import com.doran.profile.service.ProfileService;
import com.doran.user.repository.UserRepository;
import com.doran.user.service.UserService;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.common.UserInfo;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterService{
    private final UserRepository userRepository;
    private final ParentService parentService; // 부모 아이 판별을 위해 import
    private final ProfileService profileService;
    private final BucketMapper bucketMapper;
    private final BucketService bucketService;
    private final LetterMapper letterMapper;
    private final LetterRepository letterRepository;
    // 읽지 않은 편지 조회
    public LetterResDtoList getLetterList(int userId){
        List<Letter> unreadLetterList = letterRepository.findAllUnreadLetter(userId);
        List<LetterResDto> letterList = letterMapper.letterListToResDtoList(unreadLetterList);
        return letterMapper.letterListToResDtoList(letterList, letterList.size());
    }
    // 읽은 편지 갱신
    public void readLetter(int userId){
        List<Letter> unreadLetterList = letterRepository.findAllUnreadLetter(userId);
        for(Letter letter : unreadLetterList){
            letter.setModifiedDate(LocalDateTime.now());
            letterRepository.save(letter);
        }
    }
    // 편지 등록
    public Letter insertLetter(LetterInsertDto letterInsertDto){
        log.info("편지 등록");
        UserInfo userInfo = Auth.getInfo();
        Parent parent = null;
        Profile profile = null;
        int receiverId=0;
        int senderId = userInfo.getUserId();
        if(parentService.checkParent(userInfo.getUserRole().getRole())){
            // 보내는 사람 프로필(아이), 받는 사람 부모일 때
            profile = profileService.findProfileById(userInfo.getSelectProfileId());
            parent = parentService.findParentByProfileId(profile.getId());
            receiverId = parentService.getParentUserId(userInfo.getUserId(),userInfo.getUserRole().getRole());
        }else{
            // 보내는 사람 부모, 받는 사람 프로필(아이)일 때 -> 이때 부모는 null 상태로 저장
            profile = profileService.findProfileById(letterInsertDto.getProfileId());
            parent = parentService.findParentByUserId(userInfo.getUserId());
            receiverId = userRepository.findUserByProfileId(letterInsertDto.getProfileId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND)).getId();
        }
        String contentUrl = bucketService.insertFile(bucketMapper.toInsertDto(letterInsertDto.getContent(), "letter"));
        Letter letter = letterMapper.insertLettertoLetter(letterInsertDto,parent,profile,contentUrl,receiverId,senderId);
        return letterRepository.save(letter);
    }
}
