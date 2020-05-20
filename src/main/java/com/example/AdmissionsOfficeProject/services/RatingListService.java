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
import java.util.Optional;

@Service
public class RatingListService {
    private static final Logger LOG = LoggerFactory.getLogger(RatingListService.class);

    private RatingListRepository ratingListRepository;
    private StatementRepository statementRepository;
    private MailSenderService mailSenderService;

    @Autowired
    public RatingListService(RatingListRepository ratingListRepository,
                             StatementRepository statementRepository,
                             MailSenderService mailSenderService) {
        this.ratingListRepository = ratingListRepository;
        this.statementRepository = statementRepository;
        this.mailSenderService = mailSenderService;
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
        sendAcceptedMail(statementId);
    }

    public void sendAcceptedMail(int statementId){
        LOG.trace("Sending accepting statement message to user's email...");
        Optional<Statement> statement = statementRepository.findById(statementId);
        if(statement.isPresent()){
            String subject = String.format("Information about your Statement to faculty %s", statement.get().getFaculty().getTitle());
            String massage = String.format("Hello, %s %s! \n" +
                            "Your Statement was accepted by admin for faculty %s. Congratulations! \n" +
                            "Now you are participating in the competition. In your account or on the tab \"Faculties\" you can track your rating.",
                    statement.get().getUser().getFirstName(),
                    statement.get().getUser().getLastName(),
                    statement.get().getFaculty().getTitle());
            mailSenderService.send(statement.get().getUser().getEmail(), subject, massage);
        }
    }

    public void reject(int statementId, String rejectMassage) {
        ratingListRepository.reject(statementId);
        sendRejectMail(statementId);
        RatingList ratingListByStatementId = ratingListRepository.findByStatementId(statementId);
        ratingListByStatementId.setRejectMassage(rejectMassage);
        ratingListRepository.save(ratingListByStatementId);
    }

    public void sendRejectMail(int statementId){
        LOG.trace("Sending rejecting statement message to user's email...");
        Optional<Statement> statement = statementRepository.findById(statementId);
        if(statement.isPresent()){
            String subject = String.format("Information about your Statement to faculty %s", statement.get().getFaculty().getTitle());
            String massage = String.format("Hello, %s %s! \n" +
                            "Your Statement was reject by admin for faculty %s.\n" +
                            "The reason is: %s" +
                            "Please, correct this and create the application again.",
                    statement.get().getUser().getFirstName(),
                    statement.get().getUser().getLastName(),
                    statement.get().getFaculty().getTitle(),
                    statement.get().getRatingList().getRejectMassage());
            mailSenderService.send(statement.get().getUser().getEmail(), subject, massage);
        }
    }

    public List<RatingList> getRatingListIn(List<Integer> statementsIds) {
        return ratingListRepository.getRatingListByStatement(statementsIds);
    }

    public List<RatingList> getAllByAcceptedFalse() {
        return ratingListRepository.getAllByAcceptedFalseAndRejectMassageIsNull();
    }


}
