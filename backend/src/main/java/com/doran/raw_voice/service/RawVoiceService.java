package com.doran.raw_voice.service;

import com.doran.raw_voice.dto.req.RawVoiceInsertDto;
import com.doran.raw_voice.mapper.RawVoiceMapper;
import com.doran.raw_voice.dto.res.RawVoiceListDto;
import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import com.doran.raw_voice.repository.RawVoiceRepository;
import com.doran.user.entity.User;
import com.doran.user.service.UserService;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RawVoiceService {
    private final BucketMapper bucketMapper;
    private final BucketService bucketService;
    private final RawVoiceRepository rawVoiceRepository;
    private final RawVoiceMapper rawVoiceMapper;
    private final UserService userService;

    // 목소리 검색
    public RawVoice findRawVoiceById(int rvId){
        return rawVoiceRepository.findById(rvId)
                .orElseThrow(() -> new CustomException(ErrorCode.VOICE_NOT_FOUND));
    }

    // 목소리 조회
    public RawVoiceListDto getRawVoiceAll(){
         List<RawVoice> rawVoiceList = rawVoiceRepository.findAll();
         List<RawVoiceResDto> rawVoiceResDtoList = rawVoiceMapper.toDtoList(rawVoiceList);

         return new RawVoiceListDto(rawVoiceResDtoList.size(), rawVoiceResDtoList);
    }

    // 목소리 등록
   public void insertRawVoice (RawVoiceInsertDto rawVoiceInsertDto) {
        String voiceUrl = bucketService.insertFile(bucketMapper.toInsertDto(rawVoiceInsertDto.getFile(),"raw_voice"));
        User user = userService.findUser(Auth.getInfo().getUserId());
        RawVoice rawVoice = rawVoiceMapper.voiceInsertToRawVoice(user,voiceUrl);
        rawVoiceRepository.save(rawVoice);
   }

}
