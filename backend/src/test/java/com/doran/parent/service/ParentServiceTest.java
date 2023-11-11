package com.doran.parent.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;

@SpringBootTest

class ParentServiceTest {
    @Autowired
    private ParentService parentService;

    @Test
    @DisplayName("부모 아이디 조회 테스트")
    public void 부모_아이디_조회_테스트() {
        int id = parentService.getParentUserId(4, "ROLE_CHILD");
        System.out.println(id + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}