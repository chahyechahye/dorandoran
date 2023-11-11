package com.doran.utils.rabbitmq;

import java.util.Properties;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.utils.rabbitmq.dto.res.WaitResDto;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wait")
@RequiredArgsConstructor
public class RabbitController {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.model}")
    private String model;

    @GetMapping()
    public ResponseEntity getWaitCount() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);

        Properties getCount = rabbitAdmin.getQueueProperties(model);

        Integer count = (Integer)getCount.get("QUEUE_MESSAGE_COUNT");

        WaitResDto waitResDto = new WaitResDto(count, count * 60);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, waitResDto);

    }
}
