package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.FacultyRepository;
import com.example.AdmissionsOfficeProject.daos.StatementRepository;
import com.example.AdmissionsOfficeProject.daos.UserRepository;
import com.example.AdmissionsOfficeProject.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class StatementService {
    private static final Logger LOG = LoggerFactory.getLogger(StatementService.class);

    private StatementRepository statementRepository;
    private FacultyRepository facultyRepository;
    private UserRepository userRepository;
    private RatingListService ratingListService;

    @Autowired
    public StatementService(StatementRepository statementRepository,
                            FacultyRepository facultyRepository,
                            UserRepository userRepository,
                            RatingListService ratingListService) {
        this.statementRepository = statementRepository;
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
        this.ratingListService = ratingListService;
    }

    public List<Statement> findAll() {
        LOG.trace("Getting all statements...");
        return statementRepository.findAll();
    }

    public Optional<Statement> findByUser(User user) {
        LOG.trace("Getting statement by user " + user);
        return statementRepository.findByUser(user);
    }

    public boolean checkIfExist(int facultyId, int userId) {
        LOG.trace("Checking if statement by userId exists in DB...");
        return statementRepository.checkIfExist(facultyId, userId);
    }

    public void save(Statement statement,
                     int facultyId,
                     int averageCertificateMark,
                     int userId,
                     List<Certificate> certificateIds) {
        LOG.trace("Creating new statement...");

        Faculty faculty = facultyRepository.getOne(facultyId);
        statement.setFaculty(faculty);
        statement.setAverageCertificateMark(averageCertificateMark);
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(statement::setUser);

        statement.setExamMarks(new HashSet<>());
        for (Certificate certificateId : certificateIds) {
            statement.getExamMarks().add(certificateId);
        }

        //calculate average mark
        int total = 0;
        int loop = 0;
        for (Certificate certificate1 : certificateIds) {
            total += certificate1.getMark();
            loop++;
        }
        int averageExamMark = total / loop;
        statement.setAverageExamMark(averageExamMark);

        RatingList ratingList = ratingListService.save(statement);
        statement.setRatingList(ratingList);

        statementRepository.save(statement);
    }

    public List<Statement> getAllByUserId(int userId) {
        LOG.trace("Getting all statements by userId " + userId);
        return statementRepository.findAllByUserId(userId);
    }

    public void deleteById(int id) {
        LOG.trace("Deleting statement by id " + id);
        statementRepository.deleteById(id);
    }

    public List<Statement> findAllByFacultyId(int facultyId) {
        LOG.trace("Getting all statements by userId " + facultyId);
        return statementRepository.findAllByFacultyId(facultyId);
    }


}
