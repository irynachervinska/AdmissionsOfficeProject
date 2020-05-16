package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import com.example.AdmissionsOfficeProject.services.FacultyService;
import com.example.AdmissionsOfficeProject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/faculty")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class FacultyController {

    private FacultyService facultyService;
    private SubjectService subjectService;

    @Autowired
    public FacultyController(FacultyService facultyService, SubjectService subjectService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String viewFacultyList(Model model,
                                 int[] subjectIds) {
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("subjectsById", subjectService.getAllByIds(subjectIds));

        return "faculties";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/addFaculty")
    public String showCreationForm(Faculty faculty,
                                   Model model) {
        model.addAttribute("faculty", faculty);
        return "createFaculty";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addFaculty")
    public String save(@ModelAttribute @Valid Faculty faculty,
                       BindingResult bindingResult,
                       Model model) {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            assert fieldError != null;
            model.addAttribute("hasErrors", fieldError.getDefaultMessage());
            return "createFaculty";
        }

        boolean facultyExist = facultyService.checkIfExist(faculty);
        if (facultyExist){
            model.addAttribute("facultyExistError", "Faculty with such title already exists");
            return "createFaculty";
        }

        facultyService.saveFaculty(faculty);
        return "redirect:/faculty";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String deleteFaculty(@RequestParam("id") Faculty faculty) {
        facultyService.deleteById(faculty.getId());
        return "redirect:/faculty";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit")
    public String viewEditForm(@RequestParam("id") Faculty faculty,
                               Model model) {
        model.addAttribute("faculty", faculty);
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "editFaculty";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Faculty faculty,
                       @RequestParam String title,
                       @RequestParam int placesNumberPaid,
                       @RequestParam int placesNumberFree,
                       @RequestParam List <Subject> subjectIds,
                       Model model) {
        model.addAttribute("faculty", faculty);
        facultyService.edit(faculty, title, placesNumberPaid, placesNumberFree, subjectIds);
        return "redirect:/faculty";
    }

    @GetMapping("addSubjects/subjects/{id}")
    public String addSubject(@PathVariable("id") int facultyId,
                             Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("faculty", facultyService.getById(facultyId));
        return "addSubject";
    }

    @PostMapping("/subjects")
    public String facultiesAddSubject(@RequestParam("id") int facultyId,
                                      @RequestParam List <Subject> subjectIds,
                                      Model model) {
        Faculty faculty = facultyService.getById(facultyId);

        faculty.setSubjects(new HashSet<>());
        for (Subject subjectId : subjectIds) {
            faculty.getSubjects().add(subjectId);
        }
        facultyService.saveFaculty(faculty);


        model.addAttribute("faculty", facultyService.getById(facultyId));
        model.addAttribute("subjects", subjectService.getAllSubjects());

        return "redirect:/faculty";
    }


}
