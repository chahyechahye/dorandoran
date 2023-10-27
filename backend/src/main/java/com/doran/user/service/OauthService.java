package com.doran.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.type.Roles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {
    private final UserService userService;

    //회원이 존재하지 않으면 회원가입
    public UserTokenBaseDto getFindDto(String email, String nickname, Provider provider, Roles roles) {
        log.info("회원이 존재하지 않으면 회원가입");
        Optional<UserTokenBaseDto> findUser = userService.findUser(email, provider);

        return findUser.orElseGet(() -> {
            userService.signUp(nickname, email, provider, roles);
            return userService.findUser(email, provider).get();
        });
    }
}
