package com.example.AdmissionsOfficeProject.daos;

import com.example.AdmissionsOfficeProject.domain.UserPhotoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoFileRepository extends JpaRepository <UserPhotoFile, String> {
}
