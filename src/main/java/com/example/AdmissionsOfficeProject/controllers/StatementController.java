package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
    public String viewApplicationList(Model model,
                                      HttpServletRequest request) {
        model.addAttribute("statements", statementService.findAll());
        model.addAttribute("certificates", certificateService.getAll());

        int userId = (int) request.getSession().getAttribute("userId");
        List<Statement> statementsByUserId = statementService.getAllByUserId(userId);
        if (statementsByUserId.size() >= 3) {
            model.addAttribute("errorMassage", "More than 3 statements");
        }

        return "statementList";
    }

    @GetMapping("/create")
    public String viewCreationForm(HttpServletRequest request,
                                   Model model) {
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("certificates", certificateService.getAll());


        return "statementCreation";
    }

    @PostMapping("/create")
    public String createStatement(Statement statement,
                                  Certificate certificate,
                                  @RequestParam int facultyId,
                                  @RequestParam int averageCertificateMark,
                                  @RequestParam int userId,
                                  @RequestParam List<Certificate> certificateIds) {

        Faculty faculty = facultyService.getById(facultyId);
        statement.setFaculty(faculty);
        statement.setAverageCertificateMark(averageCertificateMark);
        Optional<User> user = userService.findById(userId);
        user.ifPresent(statement::setUser);

        statement.setExamMarks(new HashSet<>());
        for (Certificate certificateId : certificateIds) {
            statement.getExamMarks().add(certificateId);
        }

        //calculate average mark
//        List<Certificate> certificateList = certificateService.getAll();
        int total = 0;
        int loop = 0;
        for(Certificate certificate1 : certificateIds){
            total += certificate1.getMark();
            loop ++;
        }
        int averageExamMark = total / loop;
        statement.setAverageExamMark(averageExamMark);

        statementService.save(statement);
        return "redirect:/statement";
    }

    @GetMapping("/delete")
    public String deleteStatement(@RequestParam("id") Statement statement) {
        statementService.deleteById(statement.getId());
        return "redirect:/statement";
    }

}
