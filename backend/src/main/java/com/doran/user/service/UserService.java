package com.doran.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.child.service.ChildService;
import com.doran.parent.ParentService;
import com.doran.parent.entity.Parent;
import com.doran.parent.mapper.ParentMapper;
import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.entity.User;
import com.doran.user.mapper.UserMapper;
import com.doran.user.repository.UserRepository;
import com.doran.user.type.Roles;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

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
    private final ChildService childService;

    //로컬테스트용 회원가입
    public User signUp(User user) {
        return userRepository.save(user);
    }

    //회원가입
    public void signUp(String name, String email, Provider provider) {
        log.info("회원 가입 진행");
        log.info("유저 생성");
        User parentUser = userMapper.toUser(name, Roles.PARENT);
        User saveUser = signUp(parentUser);

        log.info("부모 생성");
        Parent parent = parentMapper.toParent(email, provider);
        parent.setUser(saveUser);
        Parent saveParent = parentService.saveParent(parent);

        log.info("아이 생성");
        User childUser = signUp(userMapper.toUser(name + "아이들", Roles.CHILD));
        childService.saveChild(saveParent, childUser);
    }

    //email로 회원 조회
    //있으면 로그인
    //없으면 회원가입
    public Optional<UserTokenBaseDto> findUser(String email, Provider provider) {
        log.info("email, provider로 회원 조회 진행");
        return userRepository.findUser(email, provider);
    }

    //유저 조회 - id
    public User findUser(int id) {
        Optional<User> findUser = userRepository.findById(id);

        return findUser.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
