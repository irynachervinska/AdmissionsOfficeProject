package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.domain.RatingList;
import com.example.AdmissionsOfficeProject.domain.Statement;
import com.example.AdmissionsOfficeProject.services.FacultyService;
import com.example.AdmissionsOfficeProject.services.RatingListService;
import com.example.AdmissionsOfficeProject.services.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RatingListController {

    private FacultyService facultyService;
    private RatingListService ratingListService;
    private StatementService statementService;

    @Autowired
    public RatingListController(FacultyService facultyService,
                                RatingListService ratingListService,
                                StatementService statementService) {
        this.facultyService = facultyService;
        this.ratingListService = ratingListService;
        this.statementService = statementService;
    }

    @GetMapping("faculty/ratingList/{id}")
    public String viewRationList(Model model,
                                 @PathVariable("id") int facultyId,
                                 RatingList ratingList){
        List<Statement> allByFacultyId = statementService.findAllByFacultyId(facultyId);

        model.addAttribute("ratingList", ratingListService.getRatingListIn(allByFacultyId));
        Faculty faculty = facultyService.getById(facultyId);
        model.addAttribute("faculty", faculty);

        return "ratingList";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/statementsToAccept")
    public String viewAllStatements(Model model){
        model.addAttribute("allStatementsToAccept", ratingListService.getAll());
        return "allStatementsToAccept";
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/statementsToAccept/accept/{statementId}")
    public String acceptStatement(@PathVariable("statementId") int statementId){
        ratingListService.accept(statementId);
        return "redirect:/statementsToAccept";
    }
}
