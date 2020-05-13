package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.User;
import com.example.AdmissionsOfficeProject.dtos.UserEditDto;
import com.example.AdmissionsOfficeProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/userProfile")
public class UserCabinetController {

    private UserService userService;

    @Autowired
    public UserCabinetController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewCabinet(HttpServletRequest request,
                              Model model){
        int userId = (int) request.getSession().getAttribute("userId");
        Optional<User> user = userService.findById(userId);
        user.ifPresent(
                user1 -> model.addAttribute("user", user.get()));
        return "userProfile";

    }

    @GetMapping("/edit/{id}")
    public String viewEditForm(@PathVariable ("id") int userId,
                               Model model,
                               HttpServletRequest request){
        userId = (int) request.getSession().getAttribute("userId");
        Optional<User> user = userService.findById(userId);
        user.ifPresent(
                user1 -> model.addAttribute("user", user.get()));
        return "userEdit";
    }

    @PostMapping("/edit")
    public String userEdit(@RequestParam ("userId") User user,
                           @ModelAttribute UserEditDto userEditDto){
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        user.setEmail(userEditDto.getEmail());
        user.setAge(userEditDto.getAge());

        userService.saveEdits(user);
        return "redirect:/userProfile";
    }


}
