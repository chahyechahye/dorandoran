package com.doran.utils.rabbitmq.service;

import java.util.Properties;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doran.utils.rabbitmq.dto.res.WaitResDto;
import com.doran.utils.rabbitmq.mapper.RabbitMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMapper rabbitMapper;

    @Value("${rabbitmq.queue.model}")
    private String model;

    public WaitResDto getWaitCount() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);

        Properties getCount = rabbitAdmin.getQueueProperties(model);

        int count = (int)getCount.get("QUEUE_MESSAGE_COUNT");
        log.info("count : {}", count);

        if (count == 0)
            count = 1;
        return rabbitMapper.toWaitResDto(count, (count * 100) + 50);
    }

}
