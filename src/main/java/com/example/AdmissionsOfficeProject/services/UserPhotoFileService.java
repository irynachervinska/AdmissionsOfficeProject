package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.UserPhotoFileRepository;
import com.example.AdmissionsOfficeProject.domain.UserPhotoFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class UserPhotoFileService {

    private UserPhotoFileRepository userPhotoFileRepository;

    @Autowired
    public UserPhotoFileService(UserPhotoFileRepository userPhotoFileRepository) {
        this.userPhotoFileRepository = userPhotoFileRepository;
    }

    public UserPhotoFile create(MultipartFile file){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try{
            UserPhotoFile userPhotoFile = new UserPhotoFile(fileName, file.getContentType(), file.getBytes());
            return userPhotoFileRepository.save(userPhotoFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName, e);
        }
    }

    public UserPhotoFile getById(String photoId){
        return userPhotoFileRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("File not found with id " + photoId));
    }
}
