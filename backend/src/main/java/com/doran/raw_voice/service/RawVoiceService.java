package com.doran.raw_voice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.raw_voice.dto.req.RawVoiceInsertDto;
import com.doran.raw_voice.dto.res.RawVoiceListDto;
import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import com.doran.raw_voice.mapper.RawVoiceMapper;
import com.doran.raw_voice.repository.RawVoiceRepository;
import com.doran.redis.record.mapper.RecordMapper;
import com.doran.redis.record.service.RecordService;
import com.doran.user.entity.User;
import com.doran.user.service.UserService;
import com.doran.utils.auth.Auth;
import com.doran.utils.bucket.mapper.BucketMapper;
import com.doran.utils.bucket.service.BucketService;
import com.doran.utils.common.Genders;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RawVoiceService {
    private final BucketMapper bucketMapper;
    private final BucketService bucketService;
    private final RawVoiceRepository rawVoiceRepository;
    private final RawVoiceMapper rawVoiceMapper;
    private final UserService userService;
    private final RecordService recordService;
    private final RecordMapper recordMapper;

    // 목소리 검색
    public RawVoice findRawVoiceById(int rvId) {
        return rawVoiceRepository.findById(rvId)
                                 .orElseThrow(() -> new CustomException(ErrorCode.VOICE_NOT_FOUND));
    }

    public List<RawVoiceResDto> findRawVoiceByUserId(int userId, Genders genders) {
        return rawVoiceRepository.findRawVoiceByUserId(userId, genders);
    }

    public RawVoiceListDto getRawVoiceByUserId(int userId) {
        List<RawVoiceResDto> rawVoiceResDtoList = rawVoiceRepository.findRawVoiceByUserId(userId, null);
        return rawVoiceMapper.listToResListDto(rawVoiceResDtoList, rawVoiceResDtoList.size());
    }

    // 목소리 조회
    public RawVoiceListDto getRawVoiceAll() {
        List<RawVoice> rawVoiceList = rawVoiceRepository.findAll();
        List<RawVoiceResDto> rawVoiceResDtoList = rawVoiceMapper.toDtoList(rawVoiceList);

        return new RawVoiceListDto(rawVoiceResDtoList.size(), rawVoiceResDtoList);
    }

    // 목소리 등록
    public void insertRawVoice(RawVoiceInsertDto rawVoiceInsertDto) {
        String voiceUrl = bucketService.insertFile(bucketMapper.toInsertDto(rawVoiceInsertDto.getFile(), "raw_voice"));
        User user = userService.findUser(Auth.getInfo().getUserId());
        RawVoice rawVoice = rawVoiceMapper.voiceInsertToRawVoice(user, voiceUrl, rawVoiceInsertDto.getGender());
        rawVoiceRepository.save(rawVoice);
    }

    @Transactional
    public void delete(int userId) {
        List<RawVoice> rawVoiceList = findRawVoiceList(userId);

        List<String> list = rawVoiceMapper.toList(rawVoiceList);

        bucketService.deleteFile(list);

        rawVoiceRepository.deleteRawVoice(list);
    }

    public List<RawVoice> findRawVoiceList(int userId) {
        return rawVoiceRepository.findRawVoiceByUserId(userId);
    }

    public void createRecordRedis(int userId, Genders gender) {
        // 낭독 여부 갱신
        recordService.findById(String.valueOf(userId)).ifPresentOrElse(record -> {
            log.info("레디스 있어욧!!!!!!!!!!!!!!!!!");
            recordService.update(userId, record, gender);
        }, () -> {
            log.info("레디스 없어욧!!!!!!!!!!!!!!!!!!!!!!!!");
            boolean maleAble = false;
            boolean femaleAble = false;

            if (gender.equals(Genders.MALE))
                maleAble = true;
            else
                femaleAble = true;
            recordService.save(recordMapper.toRecord(String.valueOf(userId), maleAble, femaleAble));
        });
    }
}
