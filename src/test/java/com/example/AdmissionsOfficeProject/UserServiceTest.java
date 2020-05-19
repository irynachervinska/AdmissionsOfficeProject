package com.example.AdmissionsOfficeProject;

import com.example.AdmissionsOfficeProject.daos.UserPhotoRepository;
import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import com.example.AdmissionsOfficeProject.domain.UserRole;
import com.example.AdmissionsOfficeProject.dtos.UserDto;
import com.example.AdmissionsOfficeProject.services.EmailSendingService;
import com.example.AdmissionsOfficeProject.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final String CONFIRM_HASH = "jh34-kkj22-knn33-jbn5-nnm54";
    public static final String FIRST_NAME = "Sam";
    public static final String LAST_NAME = "Smith";
    public static final String USER_EMAIL = "smith@gmail.com";
    private static final int USER_AGE = 20;
    private static final int USER_ID = 25;
    public static final String USER_PASSWORD = "password123";
    private static final String USER_PASSWORD_ENCODED = "encoded-password";


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailSendingService emailSendingService;

    @Mock
    private UserPhotoRepository userPhotoRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder, emailSendingService, userPhotoRepository);
    }

    @Test
    public void saveUserTest(){
        UserDto userDto = new UserDto();

        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(USER_EMAIL);
        userDto.setAge(USER_AGE);
        userDto.setPassword(USER_PASSWORD);

        when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(USER_PASSWORD_ENCODED);

        userService.save(userDto);

        User expectedUser = new User(FIRST_NAME, LAST_NAME, USER_EMAIL, USER_AGE);
        expectedUser.setHash(CONFIRM_HASH);
        expectedUser.setEmailVerified(false);
        expectedUser.setRole(Collections.singleton(UserRole.ROLE_ENROLLEE));
        expectedUser.setPassword(USER_PASSWORD_ENCODED);

        verify(userRepository).save(userCaptor.capture());

        assertThat(userCaptor.getValue())
                .isEqualToIgnoringGivenFields(expectedUser, "hash");

        verify(emailSendingService).sendVerificationEmail(eq(USER_EMAIL), anyString(), eq(FIRST_NAME), eq(LAST_NAME));
    }

    @Test
    public void isUserExistTest() {
        User user = new User();
        user.setEmail(USER_EMAIL);

        when(userRepository.findByEmail(USER_EMAIL))
                .thenReturn(Optional.of(user));

        Assert.assertFalse(userService.checkIfExists(user));

        verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        verify(emailSendingService, Mockito.times(0))
                .sendVerificationEmail(eq(USER_EMAIL), anyString(), eq(FIRST_NAME), eq(LAST_NAME));
    }

    @Test
    public void confirmEmailSuccessful() {
        User user = new User();
        user.setId(USER_ID);

        when(userRepository.findByHash(CONFIRM_HASH))
                .thenReturn(Optional.of(user));

        userService.confirmEmail(CONFIRM_HASH);

        verify(userRepository).findByHash(CONFIRM_HASH);
        verify(userRepository).isConfirmEmail(USER_ID);
    }

    @Test
    public void confirmEmailFail() {
        when(userRepository.findByHash(CONFIRM_HASH))
                .thenReturn(Optional.empty());

        userService.confirmEmail(CONFIRM_HASH);

        verify(userRepository).findByHash(CONFIRM_HASH);
        verify(userRepository, times(0)).isConfirmEmail(anyInt());
    }
}
