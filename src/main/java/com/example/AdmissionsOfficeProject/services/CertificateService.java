package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.CertificateRepository;
import com.example.AdmissionsOfficeProject.daos.SubjectRepository;
import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.Certificate;
import com.example.AdmissionsOfficeProject.domain.Subject;
import com.example.AdmissionsOfficeProject.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {
    private static final Logger LOG = LoggerFactory.getLogger(CertificateService.class);

    private CertificateRepository certificateRepository;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository,
                              SubjectRepository subjectRepository,
                              UserRepository userRepository) {
        this.certificateRepository = certificateRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public void save(Certificate certificate){
        LOG.trace("Saving certificate...");
        certificateRepository.save(certificate);
    }

    public List<Certificate> getAll(){
        LOG.trace("Getting all certificates...");
        return certificateRepository.findAll();
    }

    public void deleteById(int id){
        LOG.trace("Deleting certificate by id " + id);
        certificateRepository.deleteById(id);
    }

    public boolean checkIfExist(int subjectId, int userId){
        LOG.trace("Checking if certificate exists in DB...");
        Optional<Certificate> certificateMaybe = certificateRepository.findBySubjectId(subjectId);
        return certificateMaybe
                .filter(certificate -> certificate.getUser().getId() == userId)
                .isPresent();
    }

    public Certificate getById(int id){
        LOG.trace("Getting certificate by id " + id);
        return certificateRepository.getOne(id);
    }

    public List<Certificate> getAllByUserId(int userId){
        return certificateRepository.findAllByUserId(userId);
    }

    public void createNew(Certificate certificate, int subjectId){
        LOG.trace("Creating certificate...");
        Subject subjectById = subjectRepository.getOne(subjectId);
        certificate.setSubject(subjectById);
        certificateRepository.save(certificate);
    }
}
