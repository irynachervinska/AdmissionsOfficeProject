package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.Subject;
import com.example.AdmissionsOfficeProject.services.FacultyService;
import com.example.AdmissionsOfficeProject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

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
    public String viewFacultyList(Model model) {
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("subjects", subjectService.getAllSubjects());

        return "faculties";
    }

    @GetMapping("/addFaculty")
    public String showCreationForm(Faculty faculty, Model model) {
        model.addAttribute("faculty", faculty);
        return "createFaculty";
    }

    @PostMapping("/addFaculty")
    public String save(HttpServletRequest request,
                                @ModelAttribute Faculty faculty) {
        boolean facultyExist = facultyService.checkIfExist(faculty);
        if (facultyExist)
            return "createFaculty";

        facultyService.saveFaculty(faculty);
        return "redirect:/faculty";
    }

    @GetMapping("/delete")
    public String deleteFaculty(@RequestParam("id") Faculty faculty) {
        facultyService.deleteById(faculty.getId());
        return "redirect:/faculty";
    }

    @GetMapping("/edit")
    public String viewEditForm(HttpServletRequest req,
                               @RequestParam("id") Faculty faculty,
                               Model model) {
        model.addAttribute("faculty", faculty);
        return "editFaculty";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Faculty faculty,
                       @RequestParam String title,
                       @RequestParam int placesNumberPaid,
                       @RequestParam int placesNumberFree) {
        faculty.setTitle(title);
        faculty.setPlacesNumberPaid(placesNumberPaid);
        faculty.setPlacesNumberFree(placesNumberFree);
        facultyService.saveFaculty(faculty);

        return "redirect:/faculty";
    }

    @GetMapping("addSubjects/{id}")
    public String addSubject(@PathVariable("id") int facultyId,
                             Model model){
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("faculty", facultyService.getById(facultyId));
        return "addSubject";
    }


    @GetMapping("/{id}/subjects")
    public String facultiesAddSubject(@PathVariable("id") int facultyId,
                                      @RequestParam int subjectId,
                                      Model model) {
        Subject subject = subjectService.getById(subjectId);
        Faculty faculty = facultyService.getById(facultyId);

        if (faculty != null) {
            if (!faculty.hasSubject(subject)) {
                faculty.getSubjects().add(subject);
            }
            facultyService.saveFaculty(faculty);

            model.addAttribute("faculty", facultyService.getById(facultyId));
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "redirect:/faculty";
        }

        return "redirect:/faculty";
    }
}
