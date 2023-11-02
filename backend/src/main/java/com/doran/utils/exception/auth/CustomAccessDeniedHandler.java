package com.doran.utils.exception.auth;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("권한 핸들러 진입");

        ObjectMapper objectMapper = new ObjectMapper();
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<ErrorResponseEntity> responseEntity = ErrorResponseEntity.toResponseEntity(
            ErrorCode.AUTHENTICATION_FORBIDDEN);
        String responseBody = objectMapper.writeValueAsString(responseEntity.getBody());

        response.getWriter().write(responseBody);
    }
}
