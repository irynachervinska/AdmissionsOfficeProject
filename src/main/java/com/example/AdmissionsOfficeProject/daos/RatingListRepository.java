package com.example.AdmissionsOfficeProject.daos;

import com.example.AdmissionsOfficeProject.domain.RatingList;
import com.example.AdmissionsOfficeProject.domain.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RatingListRepository extends JpaRepository<RatingList, Integer> {

    List<RatingList> findByOrderByTotalMarkDesc();

    RatingList findByStatementId(int statementId);

    @Modifying
    @Query("Update RatingList r set r.accepted=true where r.id = :statementId")
    void accept(@Param("statementId") int statementId);

//    List<RatingList> findAllByStatement(List<Statement> allByFacultyId);
}
