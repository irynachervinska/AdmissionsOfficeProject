package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import com.example.AdmissionsOfficeProject.domain.UserRole;
import com.example.AdmissionsOfficeProject.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserDto userDto){
        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());

        String password = userDto.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        user.setRole(Collections.singleton(UserRole.ROLE_ENROLLEE));

//        if(user.getUsername().equals("admin")){
//            user.setRoles(new HashSet<UserRole>(Arrays.asList(UserRole.ROLE_USER, UserRole.ROLE_ADMIN)));
//        }
        userRepository.save(user);
    }
}
