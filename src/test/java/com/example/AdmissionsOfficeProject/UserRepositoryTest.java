package com.example.AdmissionsOfficeProject;

import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup(){
        User user = new User();
        user.setEmail("email");
        userRepository.save(user);
    }

    @Test
    public void itConfirmsEmail() {
        List<User> allUsers = userRepository.findAll();

        assertThat(allUsers).hasSize(1);
        User user = allUsers.get(0);
        assertThat(user.isEmailVerified()).isFalse();

        userRepository.isConfirmEmail(user.getId());

        List<User> updatedUsers = userRepository.findAll();

        assertThat(updatedUsers).hasSize(1);
        User updatedUser = updatedUsers.get(0);

        assertThat(updatedUser.isEmailVerified()).isTrue();
    }





}
