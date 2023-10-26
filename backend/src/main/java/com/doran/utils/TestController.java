package com.doran.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.animal.dto.res.AnimalDto;
import com.doran.animal.service.AnimalService;
import com.doran.redis.invite.key.Invite;
import com.doran.redis.invite.service.InviteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/test")
public class TestController {
    private final AnimalService animalService;
    private final InviteService inviteService;

    @GetMapping("/animal/{id}")
    public ResponseEntity<AnimalDto> selectAnimal(@PathVariable int id) {
        return ResponseEntity.ok(animalService.selectAnimal(id));
    }

    @GetMapping("/redis/id/{id}")
    public ResponseEntity findInvite(@PathVariable int id) {
        inviteService.findCode("606777");

        Invite code = inviteService.findCode(id);
        log.info("코드 : {}", code.getCode());
        log.info("유저id : {}", code.getUserId());
        return null;
    }
}
