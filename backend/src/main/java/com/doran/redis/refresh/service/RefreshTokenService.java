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
    public void save(String value) {
        RefreshToken refreshToken = refreshTokenMapper.toRefreshToken(value);

        refreshTokenRepository.save(refreshToken);
    }

    //리프레시 토큰 조회 (토큰 값으로)
    public RefreshToken findRefresh(String value) {
        Optional<RefreshToken> findRefresh = refreshTokenRepository.findById(value);

        return validRefresh(findRefresh);
    }

    //리프레시 토큰 조회 (userId로)
    public RefreshToken findRefresh(int userId) {
        Optional<RefreshToken> findRefresh = refreshTokenRepository.findRefreshTokenByUserId(userId);

        return validRefresh(findRefresh);
    }

    //있는지 체크
    public RefreshToken validRefresh(Optional<RefreshToken> findRefresh) {
        return findRefresh
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REFRESH_TOKEN));
    }
}
