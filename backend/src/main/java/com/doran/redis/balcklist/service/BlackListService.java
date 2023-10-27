package com.doran.redis.balcklist.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.balcklist.Mapper.BlackListMapper;
import com.doran.redis.balcklist.key.BlackList;
import com.doran.redis.balcklist.repository.BlackListRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlackListService {
    private final BlackListRepository blackListRepository;
    private final BlackListMapper blackListMapper;

    //저장 - 토큰 파기시킬 때 블랙리스트에 엑세스토큰을 저장하여 재사용이 불가능하게 만들어버림
    public void save(String value) {
        BlackList blackList = blackListMapper.toBlackList(value);
        blackListRepository.save(blackList);
    }

    //조회
    //조회 결과가 있다면? -> 에러 발생
    //조회 결과가 없다면 -> 통과
    public void findBlackList(String value) {
        Optional<BlackList> findBlackList = blackListRepository.findById(value);

        findBlackList.ifPresent(blackList -> {
            throw new CustomException(ErrorCode.INVALID_AUTH_CODE);
        });

    }
}
