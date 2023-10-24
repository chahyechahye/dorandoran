package com.doran.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserFindDto;
import com.doran.user.dto.res.GetKakaoToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {
	@Value("${oauth.kakao.api-key}")
	private String clientId;

	@Value("${oauth.kakao.redirect-uri}")
	private String redirectUri;

	@Value("${oauth.kakao.token-uri}")
	private String tokenUri;

	@Value("${oauth.kakao.info-uri}")
	private String infoUri;

	private final UserService userService;
	private final OauthService oauthService;

	//카카오
	//인가코드 -> 토큰 정보
	public String getToken(String code) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("client_id", clientId);
		body.add("redirect_uri", redirectUri);
		body.add("code", code);
		body.add("grant_type", "authorization_code");

		WebClient webClient = WebClient.create();

		GetKakaoToken res = webClient.post()
			.uri(tokenUri)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED) //폼 데이터 형식
			.body(BodyInserters.fromFormData(body)) //폼 데이터를 바디에 넣음
			.retrieve()
			.bodyToMono(GetKakaoToken.class)
			.block();

		log.info("res : {}", res.getAccess_token());

		//엑세스 토큰 반환
		return res.getAccess_token();
	}

	//토큰정보 -> 사용자 정보
	public UserFindDto getUserInfo(String at) throws JsonProcessingException {
		WebClient webClient = WebClient.create();
		//kakao_account.profile
		String res = webClient.post()
			.uri(infoUri)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.header("Authorization", "Bearer " + at)
			.bodyValue("property_keys=[\"kakao_account.profile\",\"kakao_account.email\"]")
			.retrieve()
			.bodyToMono(String.class)
			.block();

		log.info("res : {}", res);
		ObjectMapper mapper = new ObjectMapper();

		String nickname = mapper.readTree(res).get("kakao_account").get("profile").get("nickname").asText();
		String email = mapper.readTree(res).get("kakao_account").get("email").asText();

		log.info("nickname : {}", nickname);
		log.info("email : {}", email);

		return oauthService.getFindDto(email, nickname, Provider.kakao);
	}
}
