package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Certificate;
import com.example.AdmissionsOfficeProject.domain.RatingList;
import com.example.AdmissionsOfficeProject.domain.Statement;
import com.example.AdmissionsOfficeProject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/statement")
// TODO: 06.05.2020 remove
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class StatementController {

    private StatementService statementService;
    private FacultyService facultyService;
    private SubjectService subjectService;
    private CertificateService certificateService;
    private UserService userService;

    @Autowired
    public StatementController(StatementService statementService,
                               FacultyService facultyService,
                               SubjectService subjectService,
                               CertificateService certificateService,
                               UserService userService) {
        this.statementService = statementService;
        this.facultyService = facultyService;
        this.subjectService = subjectService;
        this.certificateService = certificateService;
        this.userService = userService;
    }

    @GetMapping
    public String viewCreationForm(Model model,
                                      HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        List<Statement> statementsByUserId = statementService.getAllByUserId(userId);

        model.addAttribute("statements", statementsByUserId);
//        model.addAttribute("certificates", certificateService.getAll());

        if (statementsByUserId.size() >= 3) {
            model.addAttribute("errorMassage", "More than 3 statements");
        }
        return "statementList";
    }

    @GetMapping("/create")
    public String viewCreationForm(Model model) {
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("certificates", certificateService.getAll());

        return "statementCreation";
    }

    @PostMapping("/create")
    public String createStatement(Statement statement,
                                  @RequestParam int facultyId,
                                  @RequestParam int averageCertificateMark,
                                  @RequestParam int userId,
                                  @RequestParam List<Certificate> certificateIds,
                                  Model model) {
//        boolean checkIfExist = statementService.checkIfExist(facultyId, userId);
//        if (checkIfExist){
//            model.addAttribute("statementExistError", "Statement for this faculty is already exists");
//            return "statementCreation";
//        }
        statementService.save(statement, facultyId, averageCertificateMark, userId, certificateIds);
        return "redirect:/statement";
    }

    @GetMapping("/delete")
    public String deleteStatement(@RequestParam("id") Statement statement) {
        statementService.deleteById(statement.getId());
        return "redirect:/statement";
    }

}
