package com.example.AdmissionsOfficeProject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailSendingService {
    private static final Logger LOG = LoggerFactory.getLogger(EmailSendingService.class);

    @Value("${appBaseDomain}")
    private String appBaseDomain;
    @Value("${verifyLink}")
    private String verifyLink;

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSendingService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendVerificationEmail(String userEmail, String hash, String userFirstName, String userLastName) {
        LOG.trace("Sending email to user with hash {}", hash);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userEmail);
        simpleMailMessage.setSubject("Please verify your email");
        String text = String.format("Hello, %s %s! \n" +
                        "Welcome to Admissions Office! \n" +
                        "Please, click link below to confirm email and finish registration: \n",
                userFirstName, userLastName);

        String string = appBaseDomain + verifyLink + hash;
        simpleMailMessage.setText(text + string);

        javaMailSender.send(simpleMailMessage);

    }
}
