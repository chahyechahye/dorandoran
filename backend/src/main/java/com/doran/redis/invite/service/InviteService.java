package com.doran.redis.invite.service;

import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.invite.key.Invite;
import com.doran.redis.invite.mapper.InviteMapper;
import com.doran.redis.invite.repository.InviteRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InviteService {
    private final InviteRepository inviteRepository;
    private final InviteMapper inviteMapper;

    //저장
    public void save(int userId) {
        Faker faker = new Faker(Locale.KOREA);
        String code = faker.numerify("######");

        Invite invite = inviteMapper.toInvite(code, userId);
        inviteRepository.save(invite);
    }

    //조회
    public Invite findCode(String code) {
        log.info("코드로 조회 : {}", code);
        Optional<Invite> findInvite = inviteRepository.findById(code);

        return validCode(findInvite);
    }

    public Invite findCode(int userId) {
        Optional<Invite> findInvite = inviteRepository.findInviteByUserId(userId);

        return validCode(findInvite);
    }

    //유효한지 검사
    public Invite validCode(Optional<Invite> findInvite) {
        return findInvite
            .orElseThrow(() -> new CustomException(ErrorCode.INVITE_CODE_NOT_FOUND));
    }

}
