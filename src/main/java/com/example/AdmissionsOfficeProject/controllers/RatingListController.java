package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Faculty;
import com.example.AdmissionsOfficeProject.services.FacultyService;
import com.example.AdmissionsOfficeProject.services.RatingListService;
import com.example.AdmissionsOfficeProject.services.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                                 @PathVariable("id") int facultyId){
        List<Integer> allIdsByFacultyId = statementService.getAllIdsByFacultyId(facultyId);

        model.addAttribute("ratingList", ratingListService.getRatingListIn(allIdsByFacultyId));
        Faculty faculty = facultyService.getById(facultyId);
        model.addAttribute("faculty", faculty);
        return "ratingList";
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/statementsToAccept")
    public String viewAllStatements(Model model){
        model.addAttribute("allStatementsToAccept", ratingListService.getAllByAcceptedFalse());
        return "allStatementsToAccept";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/statementsToAccept/accept/{statementId}")
    public String acceptStatement(@PathVariable("statementId") int statementId){
        ratingListService.accept(statementId);
        return "redirect:/statementsToAccept";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/statementsToAccept/reject/{statementId}")
    public String doReject(@PathVariable("statementId") int statementId,
                                  @RequestParam String rejectMassage){
        ratingListService.reject(statementId, rejectMassage);
        return "redirect:/statementsToAccept";
    }
}
