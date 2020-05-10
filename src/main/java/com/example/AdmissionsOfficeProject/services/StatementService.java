package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.StatementRepository;
import com.example.AdmissionsOfficeProject.domain.Statement;
import com.example.AdmissionsOfficeProject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementService {

    private StatementRepository statementRepository;

    @Autowired
    public StatementService(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    public List<Statement> findAll() {
        return statementRepository.findAll();
    }

    public Optional<Statement> findByUser(User user) {
        return statementRepository.findByUser(user);
    }

    public boolean checkIfExist(User user) {
        Optional<Statement> userMaybe = statementRepository.findByUser(user);
        return userMaybe.isPresent();
    }

    public void save(Statement statement) {
        statementRepository.save(statement);
    }

    public List<Statement> getAllByUserId(int userId) {
        return statementRepository.findAllByUserId(userId);
    }

    public void deleteById(int id){
        statementRepository.deleteById(id);
    }


}
