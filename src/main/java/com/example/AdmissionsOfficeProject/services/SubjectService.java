package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.SubjectRepository;
import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public void deleteById(int id){
        subjectRepository.deleteById(id);
    }

    public boolean checkIfExist(Subject subject){
        Optional<Subject> subjectByTitle = subjectRepository.findByTitle(subject.getTitle());
        return subjectByTitle.isPresent();
    }

    public Subject getById(int subjectId){
        return subjectRepository.getOne(subjectId);
    }
}
