package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import com.example.AdmissionsOfficeProject.domain.UserRole;
import com.example.AdmissionsOfficeProject.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private EmailSendingService emailSendingService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailSendingService emailSendingService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSendingService = emailSendingService;
    }

    public void save(UserDto userDto) {
        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());

        String password = userDto.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        user.setRole(Collections.singleton(UserRole.ROLE_ENROLLEE));
        user.setEmailVerified(false);

        UUID uuid = UUID.randomUUID();
        user.setHash(uuid.toString());


        userRepository.save(user);
        if(user.getFirstName().equals("admin")){
            user.setRole(new HashSet<UserRole>(Arrays.asList(UserRole.ROLE_ENROLLEE, UserRole.ROLE_ADMIN)));
        }
        emailSendingService.sendVerificationEmail(userDto.getEmail(), uuid.toString(), userDto.getFirstName(), userDto.getLastName());
    }

    public void confirmEmail(String hash) {
        userRepository.findByHash(hash)
                .ifPresent(user -> userRepository.isConfirmEmail(user.getId()));
    }
}
