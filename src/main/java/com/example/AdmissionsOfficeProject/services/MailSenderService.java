package com.example.AdmissionsOfficeProject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    Logger LOG = LoggerFactory.getLogger(MailSenderService.class);

    private JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String userEmail;

    public void send(String emailTo, String subject, String message) {
        LOG.trace("Sending email to {}", emailTo);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(userEmail);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

}
