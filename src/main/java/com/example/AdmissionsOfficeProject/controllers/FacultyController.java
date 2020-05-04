package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/faculty")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class FacultyController {

    private FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String viewFacultyList(Model model) {
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);

        return "faculties";
    }

    @GetMapping("/addFaculty")
    public String newFaculty(Faculty faculty, Model model) {
        model.addAttribute("faculty", faculty);
        return "createFaculty";
    }

    @PostMapping("/addFaculty")
    public String createFaculty(HttpServletRequest request,
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
//        req.setAttribute("mode", "EDIT");
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
}
