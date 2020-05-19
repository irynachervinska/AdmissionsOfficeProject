package com.example.AdmissionsOfficeProject.daos;

import com.example.AdmissionsOfficeProject.domain.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Integer> {
}
