package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Subject;
import com.example.AdmissionsOfficeProject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/subject")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String viewSubjectList(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @GetMapping("/addSubject")
    public String showCreationForm(Subject subject, Model model) {
        model.addAttribute("subject", subject);
        return "createSubject";
    }

    @PostMapping("/addSubject")
    public String createSubject(@ModelAttribute @Valid Subject subject,
                                BindingResult bindingResult,
                                Model model) {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            assert fieldError != null;
            model.addAttribute("hasErrors", fieldError.getDefaultMessage());
            return "createSubject";
        }
        if (subjectService.checkIfExist(subject)){
            model.addAttribute("subjectExistError", "Subject with such title is already exists");
            return "createSubject";
        }

        subjectService.save(subject);
        return "redirect:/subject";
    }

    @GetMapping("/delete")
    public String deleteSubject(@RequestParam("id") Subject subject) {
        subjectService.deleteById(subject.getId());
        return "redirect:/subject";
    }

    @GetMapping("/edit")
    public String viewEditForm(@RequestParam("id") Subject subject,
                               Model model) {
        model.addAttribute("subject", subject);
        return "editSubject";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Subject subject,
                       @RequestParam String title,
                       Model model) {
        if (subjectService.checkIfExist(subject)){
            model.addAttribute("subjectExistError", "Subject with such title is already exists");
            return "editSubject";
        }

        subject.setTitle(title);
        subjectService.save(subject);

        return "redirect:/subject";
    }
}
