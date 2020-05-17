package com.example.AdmissionsOfficeProject.daos;

import com.example.AdmissionsOfficeProject.domain.Certificate;
import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    Optional<Certificate> findBySubjectTitle(String title);

    Optional<Certificate> findBySubjectId(int subjectId);

    List<Certificate> findAllByUserId(int userId);
}
