package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.dtos.UserDto;
import com.example.AdmissionsOfficeProject.services.UserService;
import com.example.AdmissionsOfficeProject.validators.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    private UserDtoValidator userDtoValidator;
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService, @Qualifier("userDtoValidator") UserDtoValidator userDtoValidator) {
        this.userService = userService;
        this.userDtoValidator = userDtoValidator;
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String createNew(HttpServletRequest request, @ModelAttribute @Valid UserDto userDto, BindingResult result) {
        userDtoValidator.validate(userDto, result);
        if(result.hasErrors()){
            return "register";
        }
        userService.save(userDto);
        return "confirmEmail";
    }

    @GetMapping("/confirmEmail")
    public String confirmEmail(@RequestParam String hash) {
        userService.confirmEmail(hash);
        return "login";
    }

}
