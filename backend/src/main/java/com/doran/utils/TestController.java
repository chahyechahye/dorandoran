package com.doran.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/redis")
    public ResponseEntity findInvite(@RequestParam("code") String code,
        @RequestParam("id") int id) {
        Invite findCode = inviteService.findCode(code);
        log.info("코드 : {}", findCode.getCode());
        log.info("유저id : {}", findCode.getUserId());

        Invite findId = inviteService.findCode(id);
        log.info("코드 : {}", findId.getCode());
        log.info("유저id : {}", findId.getUserId());
        return null;
    }
}
