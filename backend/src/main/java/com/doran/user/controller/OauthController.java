package com.doran.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doran.child.dto.res.ChildDto;
import com.doran.child.service.ChildService;
import com.doran.jwt.JwtProvider;
import com.doran.profile.dto.res.ProfileListDto;
import com.doran.profile.service.ProfileService;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.service.GoogleService;
import com.doran.user.service.KakaoService;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/oauth")
public class OauthController {
    private final KakaoService kakaoService;
    private final GoogleService googleService;
    private final JwtProvider jwtProvider;
    private final ChildService childService;
    private final ProfileService profileService;

    //oauth 테스트 - 카카오
    //로직 똑같은데 나눠야 하는 이유가 있는가??
    @GetMapping("/kakao")
    public ResponseEntity kakao(@RequestParam String code, HttpServletResponse response) throws
        JsonProcessingException {

        log.info("code : {}", code);
        String token = kakaoService.getToken(code);
        UserTokenBaseDto userInfo = kakaoService.getUserInfo(token);

        String accessToken = jwtProvider.createAccessToken(userInfo);

        response.setHeader("AccessToken", accessToken);

        ChildDto childDto = childService.findChildByParentUserId(userInfo.getUserId());

        ProfileListDto profileListDto = profileService.selectAllProfile(childDto.getId());

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, profileListDto);
    }

    //oauth - google
    @GetMapping("/google")
    public ResponseEntity google(@RequestParam String code, HttpServletResponse response) throws
        JsonProcessingException {
        log.info("code : {}", code);
        String token = googleService.getToken(code);
        UserTokenBaseDto userInfo = googleService.getUserInfo(token);

        String accessToken = jwtProvider.createAccessToken(userInfo);

        response.setHeader("accessToken", accessToken);

        ChildDto childDto = childService.findChildByParentUserId(userInfo.getUserId());

        ProfileListDto profileListDto = profileService.selectAllProfile(childDto.getId());
        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, profileListDto);
    }

}
