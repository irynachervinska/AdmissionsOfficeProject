package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.SubjectRepository;
import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private static final Logger LOG = LoggerFactory.getLogger(SubjectService.class);

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void save(Subject subject){
        LOG.trace("Saving subject...");
        subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects(){
        LOG.trace("Getting all subjects list...");
        return subjectRepository.findAll();
    }

    public void deleteById(int id){
        LOG.trace("Deleting subject by id " + id);
        subjectRepository.deleteById(id);
    }

    public boolean checkIfExist(String title){
        LOG.trace("Checking if subject exists in DB...");
        Optional<Subject> subjectByTitle = subjectRepository.findByTitle(title);
        return subjectByTitle.isPresent();
    }

    public Subject getById(int subjectId){
        LOG.trace("Getting subject by id " + subjectId);
        return subjectRepository.getOne(subjectId);
    }

    public List<Subject> getAllByIds(int[] subjectIds){
        LOG.trace("Getting all subjects by ids " + Arrays.toString(subjectIds));
        return subjectRepository.findSubjectsByIds(subjectIds);
    }
}
