package com.doran.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	//oauth 테스트 - 카카오
	@GetMapping("/kakao")
	public ResponseEntity kakao(@RequestParam String code) throws JsonProcessingException {
		log.info("code : {}", code);
		String token = oauthService.getToken(code);
		oauthService.getUserInfo(token);
		return null;
	}
}
