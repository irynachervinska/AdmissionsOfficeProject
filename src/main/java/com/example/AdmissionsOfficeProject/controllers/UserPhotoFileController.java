package com.example.SpringMVC.controllers;

import com.example.AdmissionsOfficeProject.domain.UserPhotoFile;
import com.example.AdmissionsOfficeProject.services.UserPhotoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserPhotoFileController {

    private UserPhotoFileService userPhotoFileService;

    @Autowired
    public UserPhotoFileController(UserPhotoFileService userPhotoFileService) {
        this.userPhotoFileService = userPhotoFileService;
    }

    @PostMapping("/user-photo/upload")
    public String uploadPhoto(@RequestParam("userPhoto") MultipartFile file){
        UserPhotoFile userPhotoFile = userPhotoFileService.create(file);
        return userPhotoFile.getId();
    }

    @GetMapping("/user-photo/download/{photoId}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable String photoId){
        UserPhotoFile photoFile = userPhotoFileService.getById(photoId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photoFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + photoFile.getFileName())
                .body(new ByteArrayResource(photoFile.getData()));
    }
}