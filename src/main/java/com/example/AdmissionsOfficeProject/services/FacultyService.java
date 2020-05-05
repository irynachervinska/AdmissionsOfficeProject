package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.FacultyRepository;
import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }

    public void saveFaculty(Faculty faculty){
//        faculty.setSubjects(subjects);
        facultyRepository.save(faculty);
    }

    public boolean checkIfExist(Faculty faculty){
        Optional<Faculty> facultyByTitle = facultyRepository.findByTitle(faculty.getTitle());
        return facultyByTitle.isPresent();
    }

    public void deleteById(int id){
        facultyRepository.deleteById(id);
    }

    public Faculty getById(int facultyId){
        return facultyRepository.getOne(facultyId);
    }

}
