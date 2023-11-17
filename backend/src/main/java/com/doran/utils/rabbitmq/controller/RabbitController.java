package com.doran.utils.rabbitmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.utils.rabbitmq.service.RabbitService;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wait")
@RequiredArgsConstructor
public class RabbitController {
    private final RabbitService rabbitService;

    @GetMapping()
    public ResponseEntity getWaitCount() {

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, rabbitService.getWaitCount());
    }
}
