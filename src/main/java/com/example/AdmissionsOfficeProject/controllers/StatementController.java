package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.*;
import com.example.AdmissionsOfficeProject.dtos.UserEditDto;
import com.example.AdmissionsOfficeProject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/statement")
@PreAuthorize("hasRole('ROLE_ENROLLEE')")
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

        if (statementsByUserId.size() >= 3) {
            model.addAttribute("errorMassage", "You can`t add more than 3 Statements");
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
    public String createStatement(@Valid Statement statement,
                                  BindingResult bindingResult,
                                  @RequestParam int facultyId,
                                  @RequestParam int averageCertificateMark,
                                  @RequestParam int userId,
                                  @RequestParam List<Certificate> certificateIds,
                                  Model model) {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            assert fieldError != null;
            model.addAttribute("hasErrors", fieldError.getDefaultMessage());
            return "statementCreation";
        }

        boolean checkIfExist = statementService.checkIfExist(facultyId, userId);
        if (checkIfExist){
            model.addAttribute("statementExistError", "Statement for this faculty is already exists");
            return "statementCreation";
        }
        statementService.save(statement, facultyId, averageCertificateMark, userId, certificateIds);
        return "redirect:/statement";
    }

    @GetMapping("/delete")
    public String deleteStatement(@RequestParam("id") Statement statement) {
        statementService.deleteById(statement.getId());
        return "redirect:/statement";
    }

    @GetMapping("/edit")
    public String viewEditForm(@RequestParam("id") Statement statement,
                               Model model) {
        model.addAttribute("statements", statementService.findAll());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("certificates", certificateService.getAll());
        return "statementEdit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Statement statement,
                       @RequestParam int facultyId,
                       @RequestParam int averageCertificateMark,
                       @RequestParam Set<Certificate> certificateList,
                       Model model){
        statementService.saveEdits(statement, facultyId, averageCertificateMark, certificateList);
        return "redirect:/statement";
    }

}
