package com.doran.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.doran.redis.refresh.service.RefreshTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws
        ServletException, IOException {
        log.info("doFilter 메서드 실행");

        String accessToken = jwtProvider.getAccessToken(request);

        log.info("accessToken : {}", accessToken);

        Claims userInfo = null;

        if (accessToken != null && jwtProvider.isTokenValid(accessToken)) {
            log.info("엑세스 토큰 유효함");

            jwtProvider.checkBlackList(accessToken);
            log.info("블랙리스트에 없음");

            setAuthentication(accessToken);
        } else if (accessToken != null) {
            log.info("엑세스 만료됨");
            jwtProvider.checkBlackList(accessToken);
            log.info("블랙리스트에 없음");

            try {
                jwtProvider.getUserInfo(accessToken);
            } catch (ExpiredJwtException e) {
                log.info("엑세스 유효하지 않음");
                userInfo = e.getClaims();
            }

            int userId = (int)userInfo.get("userId");
            log.info("userId : {}", userId);

            String refreshToken = refreshTokenService.findRefresh(userId).getValue();

            jwtProvider.isTokenValid(refreshToken);
            log.info("리프레시 토큰 유효함");

            setAuthentication(refreshToken);
        }
        filterChain.doFilter(request, response);
    }

    //토큰에서 사용자 정보 추출 후 인메모리에 저장
    private void setAuthentication(String token) {
        Authentication authentication = jwtProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
