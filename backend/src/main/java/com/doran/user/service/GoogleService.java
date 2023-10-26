package com.doran.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.dto.res.GetToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {
	@Value("${oauth.google.api-key}")
	private String clientId;

	@Value("${oauth.google.client-secret}")
	private String clientSecret;

	@Value("${oauth.google.redirect-uri}")
	private String redirectUri;

	@Value("${oauth.google.token-uri}")
	private String tokenUri;

	@Value("${oauth.google.resource-uri}")
	private String resourceUri;

	private final OauthService oauthService;

	//토큰 받아오기
	public String getToken(String code) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("client_id", clientId);
		body.add("client_secret", clientSecret);
		body.add("redirect_uri", redirectUri);
		body.add("code", code);
		body.add("grant_type", "authorization_code");

		WebClient webClient = WebClient.create();

		GetToken res = webClient.post()
			.uri(tokenUri)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED) //폼 데이터 형식
			.body(BodyInserters.fromFormData(body)) //폼 데이터를 바디에 넣음
			.retrieve()
			.bodyToMono(GetToken.class)
			.block();

		log.info("accessToken : {}", res.getAccess_token());

		return res.getAccess_token();
	}

	//test
	public UserTokenBaseDto getUserInfo(String at) throws JsonProcessingException {
		WebClient webClient = WebClient.create();

		String res = webClient.get()
			.uri(resourceUri)
			.header("Authorization", "Bearer " + at)
			.retrieve()
			.bodyToMono(String.class)
			.block();

		log.info("block : {}", res);

		ObjectMapper mapper = new ObjectMapper();
		String email = mapper.readTree(res).get("email").asText();
		String name = mapper.readTree(res).get("name").asText();

		log.info("email : {}", email);
		log.info("name : {}", name);

		return oauthService.getFindDto(email, name, Provider.goolge);
	}
}
