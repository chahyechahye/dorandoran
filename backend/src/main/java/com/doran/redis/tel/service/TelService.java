package com.doran.redis.tel.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.tel.mapper.TelMapper;
import com.doran.redis.tel.key.Tel;
import com.doran.redis.tel.repository.TelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelService {
    private final TelRepository telRepository;
    private final TelMapper telMapper;

    public void save(int userId, String userTel) {
        Tel tel = telMapper.toTel(String.valueOf(userId), userTel);
        telRepository.save(tel);
    }

    public Optional<Tel> findByUserId(int userId) {
        return telRepository.findById(String.valueOf(userId));
    }
}
