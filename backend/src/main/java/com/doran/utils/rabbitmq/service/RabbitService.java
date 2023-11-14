package com.doran.utils.rabbitmq.service;

import java.util.Properties;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doran.utils.rabbitmq.dto.res.WaitResDto;
import com.doran.utils.rabbitmq.mapper.RabbitMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMapper rabbitMapper;

    @Value("${rabbitmq.queue.model}")
    private String model;

    public WaitResDto getWaitCount() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);

        Properties getCount = rabbitAdmin.getQueueProperties(model);

        int count = (int)getCount.get("QUEUE_MESSAGE_COUNT") - 1;

        return rabbitMapper.toWaitResDto(count, count * 100);
    }

}
