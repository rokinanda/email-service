package com.jap.microservice.emailservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailService {
    private final MailSender mailSender;

    @Autowired
    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    void sendEmail(String to, String subject, String body) {
        log.debug("sent to {}, subject: {}, body: {}", to, subject, body);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
