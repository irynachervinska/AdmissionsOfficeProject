package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.FacultyRepository;
import com.example.AdmissionsOfficeProject.domain.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        facultyRepository.save(faculty);
    }

    public boolean checkIfExist(Faculty faculty){
        Optional<Faculty> facultyByTitle = facultyRepository.findByTitle(faculty.getTitle());
        return facultyByTitle.isPresent();
    }

    public void deleteById(int id){
        facultyRepository.deleteById(id);
    }

}
