package com.example.AdmissionsOfficeProject.configs;


import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private UserRepository userRepository;

    @Autowired
    public AuthenticationSuccessHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException{

        String email  = authentication.getName();

        Optional<User> userByEmail = userRepository.findByEmail(email);
        if(userByEmail.isPresent()){
            String firstName = userByEmail.get().getFirstName();
            request.getSession().setAttribute("firstName", firstName);

            String lastName = userByEmail.get().getLastName();
            request.getSession().setAttribute("lastName", lastName);

            int userId = userByEmail.get().getId();
            request.getSession().setAttribute("userId", userId);

            request.getSession().setAttribute("user", userByEmail);
        }

        response.sendRedirect("/");
    }
}
