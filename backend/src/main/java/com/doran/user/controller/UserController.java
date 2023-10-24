package com.doran.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.user.dto.req.UserJoinDto;
import com.doran.user.mapper.UserMapper;
import com.doran.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;
	private final UserMapper userMapper;

	//자체 회원가입 - 로컬 테스트용
	//부모 아이 상관없는 그냥 깡 유저 - admin 생성용
	@PostMapping("")
	public ResponseEntity join(@RequestBody UserJoinDto userJoinDto) {
		userService.signUp(userMapper.toUser(userJoinDto.getName(), userJoinDto.getUserRole()));

		return ResponseEntity.ok("오키");
	}
}
