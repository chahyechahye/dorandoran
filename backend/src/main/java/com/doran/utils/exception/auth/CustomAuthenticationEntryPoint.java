package com.doran.utils.exception.auth;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.doran.utils.exception.dto.ErrorCode;
import com.doran.utils.exception.dto.ErrorResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        log.info("인증 에러 발생");
        String ex = response.getHeader("ex");
        log.info("ex : {}", ex);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<ErrorResponseEntity> responseEntity = null;

        if (ex != null && ex.equals("407")) {
            log.info("리프레시 토큰까지 만료되었음");
            log.info("강제 로그아웃 진행");
            response.setStatus(HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value());
            responseEntity = ErrorResponseEntity.toResponseEntity(
                ErrorCode.EXPIRATION_REFRESH_TOKEN);
        } else {
            log.info("401 에러 진행");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = ErrorResponseEntity.toResponseEntity(
                ErrorCode.USER_UNAUTHORIZED);
        }

        String responseBody = objectMapper.writeValueAsString(responseEntity.getBody());

        response.getWriter().write(responseBody);
    }
}
