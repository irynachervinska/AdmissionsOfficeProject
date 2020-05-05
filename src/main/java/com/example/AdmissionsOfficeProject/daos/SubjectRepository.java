package com.example.AdmissionsOfficeProject.daos;

import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<Subject> findByTitle(String title);
}
