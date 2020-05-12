package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.FacultyRepository;
import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacultyService {
    private static final Logger LOG = LoggerFactory.getLogger(FacultyService.class);

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFaculties(){
        LOG.trace("Getting all faculties...");
        return facultyRepository.findAll();
    }

    public void saveFaculty(Faculty faculty){
        LOG.trace("Saving faculty...");
        facultyRepository.save(faculty);
    }

    public boolean checkIfExist(Faculty faculty){
        LOG.trace("Checking if faculty exists in DB...");
        Optional<Faculty> facultyByTitle = facultyRepository.findByTitle(faculty.getTitle());
        return facultyByTitle.isPresent();
    }

    public void deleteById(int id){
        LOG.trace("Deleting faculty by id " + id);
        facultyRepository.deleteById(id);
    }

    public Faculty getById(int facultyId){
        LOG.trace("Getting faculty by id " + facultyId);
        return facultyRepository.getOne(facultyId);
    }

}
