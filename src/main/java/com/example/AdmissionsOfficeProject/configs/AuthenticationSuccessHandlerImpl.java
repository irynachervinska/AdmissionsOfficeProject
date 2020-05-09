package com.example.AdmissionsOfficeProject.configs;


import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

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
