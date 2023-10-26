package com.doran.redis.invite.service;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.doran.redis.invite.key.Invite;
import com.doran.redis.invite.mapper.InviteMapper;
import com.doran.redis.invite.repository.InviteRepository;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
}
