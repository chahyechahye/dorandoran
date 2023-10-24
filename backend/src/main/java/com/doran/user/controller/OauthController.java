package com.doran.user.controller;

import com.doran.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doran.user.dto.req.UserFindDto;
import com.doran.user.service.KakaoService;
import com.doran.user.service.OauthService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/oauth")
public class OauthController {
	private final OauthService oauthService;
	private final KakaoService kakaoService;
	private final JwtProvider jwtProvider;

	//oauth 테스트 - 카카오
	@GetMapping("/kakao")
	public ResponseEntity kakao(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
		log.info("code : {}", code);
		String token = kakaoService.getToken(code);
		UserFindDto userInfo = kakaoService.getUserInfo(token);

		String accessToken = jwtProvider.createAccessToken(userInfo);
		String refreshToken = jwtProvider.createRefreshToken(userInfo);

		response.setHeader("accessToken",accessToken);
		response.setHeader("refreshToken",refreshToken);

		return ResponseEntity.ok("test 진행");
	}
}
