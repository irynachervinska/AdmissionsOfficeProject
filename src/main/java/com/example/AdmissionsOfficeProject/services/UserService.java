package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.UserPhotoRepository;
import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import com.example.AdmissionsOfficeProject.domain.UserPhoto;
import com.example.AdmissionsOfficeProject.domain.UserRole;
import com.example.AdmissionsOfficeProject.dtos.UserDto;
import com.example.AdmissionsOfficeProject.dtos.UserEditDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private EmailSendingService emailSendingService;
    private UserPhotoRepository userPhotoRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmailSendingService emailSendingService,
                       UserPhotoRepository userPhotoRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSendingService = emailSendingService;
        this.userPhotoRepository = userPhotoRepository;
    }

    public void save(UserDto userDto) {
        LOG.trace("Creating new user...");
        User user = new User();

        if (checkIfExists(user))
            return;

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());

        String password = userDto.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        user.setRole(Collections.singleton(UserRole.ROLE_ENROLLEE));
        if (user.getFirstName().equals("admin")) {
            user.setRole(Collections.singleton(UserRole.ROLE_ADMIN));
        }

        user.setEmailVerified(false);

        UUID uuid = UUID.randomUUID();
        user.setHash(uuid.toString());

        userRepository.save(user);
        emailSendingService.sendVerificationEmail(userDto.getEmail(), uuid.toString(), userDto.getFirstName(), userDto.getLastName());
    }

    public void confirmEmail(String hash) {
        LOG.trace("Confirming usr email...");
        userRepository.findByHash(hash)
                .ifPresent(user -> userRepository.isConfirmEmail(user.getId()));
    }

    public Optional<User> findByEmail(String email) {
        LOG.trace("Find user by email " + email);
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(int id) {
        LOG.trace("Find user by id " + id);
        return userRepository.findById(id);
    }

    public void saveEdits(User user,
                          UserEditDto userEditDto,
                          MultipartFile photo) {
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        user.setEmail(userEditDto.getEmail());
        user.setAge(userEditDto.getAge());
        userRepository.save(user);

//        List<UserPhoto> userPhotos = Arrays.stream(photos)
//                .map(this::mapToBytes)
//                .filter(bytes -> bytes.length > 0)
//                .map(bytes -> Base64.getEncoder().encodeToString(bytes))
//                .map(encodedString -> new UserPhoto(encodedString, user))
//                .collect(Collectors.toList());

        byte[] bytes = mapToBytes(photo);
        String encode = Base64.getEncoder().encodeToString(bytes);
        UserPhoto userPhoto = new UserPhoto(encode, user);
        userPhotoRepository.save(userPhoto);
    }

    public boolean checkIfExists(User user) {
        LOG.trace("Checking if user is already exists in DB...");

        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());

        if (byEmail.isPresent()) {
            LOG.warn("User with email \"" + user.getEmail() + "\"  is already exists in database...");
            return false;
        }
        return true;
    }

    private byte[] mapToBytes(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            System.out.println("Can't save file with name " + multipartFile.getName());
            return new byte[]{};
        }
    }
}
