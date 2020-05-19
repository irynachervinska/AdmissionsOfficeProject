package com.example.AdmissionsOfficeProject.controllers;

import com.example.AdmissionsOfficeProject.domain.Certificate;
import com.example.AdmissionsOfficeProject.services.CertificateService;
import com.example.AdmissionsOfficeProject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RequestMapping("/certificate")
@PreAuthorize("hasRole('ROLE_ENROLLEE')")
@Controller
public class CertificateController {

    private CertificateService certificateService;
    private SubjectService subjectService;

    @Autowired
    public CertificateController(CertificateService certificateService,
                                 SubjectService subjectService) {
        this.certificateService = certificateService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String viewCertificateList(HttpServletRequest request,
                                      Model model) {
        int userId = (int) request.getSession().getAttribute("userId");
        model.addAttribute("userId", userId);
        model.addAttribute("certificates", certificateService.getAllByUserId(userId));

        return "certificateList";
    }

    @GetMapping("/add")
    public String showCreationForm(Certificate certificate,
                                   Model model) {
        model.addAttribute("certificate", certificate);
        model.addAttribute("subjects", subjectService.getAllSubjects());

        return "createCertificate";
    }

    @PostMapping("/add")
    public String createCertificate(@ModelAttribute @Valid Certificate certificate,
                                    BindingResult bindingResult,
                                    RedirectAttributes attr,
                                    @RequestParam int subjectId,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            assert fieldError != null;
            model.addAttribute("hasErrors", fieldError.getDefaultMessage());
            return "createCertificate";
//            return "redirect:/certificate/add";
        }
        if (certificateService.checkIfExist(subjectId)) {
            model.addAttribute("certExistError", "Subject with such title is already exists");
            return "createCertificate";
        }
        certificateService.createNew(certificate, subjectId);
        return "redirect:/certificate";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Certificate certificate) {
        certificateService.deleteById(certificate.getId());
        return "redirect:/certificate";
    }

    @GetMapping("/edit")
    public String viewEditForm(@RequestParam("id") Certificate certificate,
                               Model model) {
        model.addAttribute("certificate", certificate);
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "editCertificate";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Certificate certificate,
                       @RequestParam int subjectId,
                       @RequestParam int mark) {
        certificate.setSubject(subjectService.getById(subjectId));
        certificate.setMark(mark);
        certificateService.save(certificate);

        return "redirect:/certificate";
    }
}


