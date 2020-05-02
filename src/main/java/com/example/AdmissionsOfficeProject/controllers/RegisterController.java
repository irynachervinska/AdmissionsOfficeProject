package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.dtos.UserDto;
import com.example.AdmissionsOfficeProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(HttpServletRequest req) {
        return "register";
    }

    @PostMapping("/register")
    public String createNew (HttpServletRequest request, @ModelAttribute UserDto userDto){
        userService.save(userDto);
        return "confirmEmail";
    }

    @GetMapping("/confirmEmail")
    public String confirmEmail(@RequestParam String hash){
        userService.confirmEmail(hash);
        return "login";
    }

}
