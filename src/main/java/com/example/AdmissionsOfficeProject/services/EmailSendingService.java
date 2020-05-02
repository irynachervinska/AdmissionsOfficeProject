package com.example.AdmissionsOfficeProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSendingService {

    @Value("${appBaseDomain}")
    private String appBaseDomain;
    @Value("${verifyLink}")
    private String verifyLink;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String userEmail, String hash, String userFirstName, String userLastName) {
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
