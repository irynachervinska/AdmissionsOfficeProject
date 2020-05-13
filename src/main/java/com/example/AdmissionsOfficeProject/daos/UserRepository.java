package com.example.AdmissionsOfficeProject.daos;

import com.example.AdmissionsOfficeProject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByHash(String hash);

    @Modifying
    @Transactional
    @Query("update User u set u.isEmailVerified=true where u.id = :userId")
    void isConfirmEmail(@Param("userId") int userId);

//    @Query("select u.userPhotoId from User u where u.id = :userId")
//    Optional<String> getUserPhotoIdByUserId(@Param("userId") int userId);
}
