package com.jap.microservice.emailservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jap.microservice.emailservice.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {
    private final EmailService emailService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RedisMessageSubscriber(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = message.toString();
        System.out.println("tes bang");
        try {
            EmailDto emailDto = objectMapper.readValue(msg, EmailDto.class);
            emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
