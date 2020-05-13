package com.example.AdmissionsOfficeProject.services;

import com.example.AdmissionsOfficeProject.daos.RatingListRepository;
import com.example.AdmissionsOfficeProject.daos.StatementRepository;
import com.example.AdmissionsOfficeProject.domain.RatingList;
import com.example.AdmissionsOfficeProject.domain.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingListService {
    private static final Logger LOG = LoggerFactory.getLogger(RatingListService.class);

    private RatingListRepository ratingListRepository;
    private StatementRepository statementRepository;

    @Autowired
    public RatingListService(RatingListRepository ratingListRepository,
                             StatementRepository statementRepository) {
        this.ratingListRepository = ratingListRepository;
        this.statementRepository = statementRepository;
    }

    public List<RatingList> getAll() {
        LOG.trace("Getting all rating lists...");
        return ratingListRepository.findAll();
    }

    public List<RatingList> findByOrderByTotalMark() {
        LOG.trace("Get RatingList order ny total mark");
        return ratingListRepository.findByOrderByTotalMarkDesc();
    }

    public RatingList save(Statement statement) {
        LOG.trace("Initializing rating list for specified faculty Ids... ");
        RatingList ratingList = new RatingList();
        ratingList.setStatement(statement);
        int averageExamMark = statement.getAverageExamMark();
        int averageCertificateMark = statement.getAverageCertificateMark();
        ratingList.setTotalMark(averageCertificateMark + averageExamMark);
        ratingList.setId(statement.getId());

        return ratingList;
    }

    public RatingList findByStatementId(int statementId) {
        LOG.trace("Getting rating list by statementId " + statementId);
        return ratingListRepository.findByStatementId(statementId);
    }

    public void accept(int statementId) {
        ratingListRepository.accept(statementId);
    }

    public List<RatingList> getRatingListIn(List<Integer> statementsIds){
        return ratingListRepository.getRatingListByStatement(statementsIds);
    }


}
