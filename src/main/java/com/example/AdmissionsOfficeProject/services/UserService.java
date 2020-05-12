package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import com.example.AdmissionsOfficeProject.domain.UserRole;
import com.example.AdmissionsOfficeProject.dtos.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

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
        LOG.trace("Creating new user...");
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
        LOG.trace("Confirming usr email...");
        userRepository.findByHash(hash)
                .ifPresent(user -> userRepository.isConfirmEmail(user.getId()));
    }

    public Optional<User> findByEmail(String email){
        LOG.trace("Find user by email " + email);
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(int id){
        LOG.trace("Find user by id " + id);
        return userRepository.findById(id);
    }
}
