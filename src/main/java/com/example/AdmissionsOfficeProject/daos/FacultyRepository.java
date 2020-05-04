package com.example.AdmissionsOfficeProject.daos;

import com.example.AdmissionsOfficeProject.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    Optional<Faculty> findByTitle(String title);
}
