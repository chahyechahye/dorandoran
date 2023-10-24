package com.doran.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.exception.dto.CustomException;
import com.doran.exception.dto.ErrorCode;
import com.doran.parent.ParentService;
import com.doran.parent.entity.Parent;
import com.doran.parent.mapper.ParentMapper;
import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserFindDto;
import com.doran.user.entity.User;
import com.doran.user.mapper.UserMapper;
import com.doran.user.repository.UserRepository;
import com.doran.user.type.Roles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ParentMapper parentMapper;
	private final ParentService parentService;

	//로컬테스트용 회원가입
	public User signUp(User user) {
		return userRepository.save(user);
	}

	//회원가입
	public void signUp(String name, String email, Provider provider) {
		User user = userMapper.toUser(name, Roles.PARENT);

		User saveUser = signUp(user);

		Parent parent = parentMapper.toParent(email, provider);
		parent.setUser(saveUser);
		parentService.saveParent(parent);
	}

	//email로 회원 조회
	//있으면 로그인
	//없으면 회원가입
	public Optional<UserFindDto> findUser(String email, Provider provider) {
		return userRepository.findUser(email, provider);
	}

	//유저 조회 - id
	public User findUser(int id) {
		Optional<User> findUser = userRepository.findById(id);

		return findUser
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
	}
}
