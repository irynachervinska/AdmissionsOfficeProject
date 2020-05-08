package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.CertificateRepository;
import com.example.AdmissionsOfficeProject.daos.SubjectRepository;
import com.example.AdmissionsOfficeProject.domain.Certificate;
import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {

    private CertificateRepository certificateRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public void save(Certificate certificate){
        certificateRepository.save(certificate);
    }

    public List<Certificate> getAll(){
        return certificateRepository.findAll();
    }

    public void deleteById(int id){
        certificateRepository.deleteById(id);
    }

//    public boolean checkIfExist(Certificate certificate){
//        Optional<Certificate> certificateMaybe = certificateRepository.findByTitle(certificate.getTitle());
//        return certificateMaybe.isPresent();
//    }

    public Certificate getById(int id){
        return certificateRepository.getOne(id);
    }
}
