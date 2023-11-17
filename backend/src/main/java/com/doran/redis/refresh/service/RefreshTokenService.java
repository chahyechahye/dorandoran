package com.doran.redis.refresh.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.refresh.key.RefreshToken;
import com.doran.redis.refresh.mapper.RefreshTokenMapper;
import com.doran.redis.refresh.repository.RefreshTokenRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    //리프레시 토큰 저장
    public void save(String value, int userId) {
        RefreshToken refreshToken = refreshTokenMapper.toRefreshToken(value, userId);

        refreshTokenRepository.save(refreshToken);
    }

    //리프레시 토큰 조회 (토큰 값으로)
    public Optional<RefreshToken> findRefresh(int userId) {
        return refreshTokenRepository.findById(String.valueOf(userId));
    }

    //리프레시 토큰 조회 (userId로)
    public RefreshToken findRefresh(String value) {
        Optional<RefreshToken> findRefresh = refreshTokenRepository.findRefreshTokenByValue(value);
        return validRefresh(findRefresh);
    }

    //있는지 체크
    public RefreshToken validRefresh(Optional<RefreshToken> findRefresh) {
        return findRefresh
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REFRESH_TOKEN));
    }
}
