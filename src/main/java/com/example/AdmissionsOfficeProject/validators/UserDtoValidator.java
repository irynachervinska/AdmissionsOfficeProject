package com.example.AdmissionsOfficeProject.validators;

import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import com.example.AdmissionsOfficeProject.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserDtoValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        if (userDto.getFirstName().isEmpty()){
            errors.rejectValue( "firstName", "", "First name can`t be empty");
        }
        if (userDto.getLastName().isEmpty()){
            errors.rejectValue( "lastName", "", "Last name can`t be empty");
        }
        if (userDto.getEmail().isEmpty()){
            errors.rejectValue( "email", "", "Email can`t be empty");
        }
        if (userDto.getAge() == 0){
            errors.rejectValue( "age", "", "Age can`t be empty");
        }
        if (userDto.getPassword().isEmpty()){
            errors.rejectValue( "password", "", "Password can`t be empty");
        }

        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent()){
            errors.rejectValue(
                    "email", "", "This email is already in use"
            );
        }

        if(!userDto.getPassword().equals(userDto.getPasswordConfirm())){
            errors.rejectValue(
                    "passwordConfirm", "", "Passwords is different"
            );
        }
    }
}
