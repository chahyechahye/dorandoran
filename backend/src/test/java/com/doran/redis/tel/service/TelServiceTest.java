package com.doran.redis.tel.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TelServiceTest {
    @Autowired
    private TelService telService;

    @Test
    public void 문자_레디스_테스트() {
        telService.save(17,"01091235632");
        log.info(telService.findByUserId(17).get().getTel());
    }

}