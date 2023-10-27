package com.doran.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.jwt.JwtProvider;
import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserJoinDto;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.service.OauthService;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    private final OauthService oauthService;
    private final JwtProvider jwtProvider;

    //자체 회원가입 - 로컬 테스트용
    //부모 아이 상관없는 그냥 깡 유저 - admin 생성용
    @PostMapping("")
    public ResponseEntity join(@RequestBody UserJoinDto userJoinDto, HttpServletResponse response) {
        UserTokenBaseDto findDto = oauthService.getFindDto(userJoinDto.getEmail(), userJoinDto.getName(),
            Provider.local,
            userJoinDto.getUserRole());

        String accessToken = jwtProvider.createAccessToken(findDto);
        String refreshToken = jwtProvider.createRefreshToken(findDto);

        response.setHeader("AccessToken", accessToken);
        response.setHeader("RefreshToken", refreshToken);

        return CommonResponseEntity
            .getResponseEntity(SuccessCode.OK);
    }
}
