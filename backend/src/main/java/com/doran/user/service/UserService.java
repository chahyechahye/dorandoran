package com.doran.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.exception.dto.CustomException;
import com.doran.exception.dto.ErrorCode;
import com.doran.user.entity.User;
import com.doran.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;

	//로컬테스트용 회원가입
	public void joinUser(User user) {
		userRepository.save(user);
	}

	//유저 조회 - id
	public User findUser(int id) {
		Optional<User> findUser = userRepository.findById(id);

		return findUser
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
	}
}
