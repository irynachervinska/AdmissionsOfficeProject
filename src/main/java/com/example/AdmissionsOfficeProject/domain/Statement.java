package com.example.AdmissionsOfficeProject.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "statements")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "average_certificate_mark")
    @Min(value = 100, message = "Mark can`t be less, than 100!")
    @Max(value = 200, message = "Mark can`t be more, than 200!")
    private int averageCertificateMark;

    @ManyToMany
    @JoinTable(name = "statement_examMarks_mapping",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "statement_id"))
    private Set<Certificate> examMarks;

    @Column(name = "average_exam_mark")
    private int averageExamMark;

    @OneToOne(mappedBy = "statement", cascade = CascadeType.ALL)
    private RatingList ratingList;

    public Statement() {
    }

    public RatingList getRatingList() {
        return ratingList;
    }

    public void setRatingList(RatingList ratingList) {
        this.ratingList = ratingList;
    }

    public int getAverageExamMark() {
        return averageExamMark;
    }

    public void setAverageExamMark(int averageExamMark) {
        this.averageExamMark = averageExamMark;
    }

    public Set<Certificate> getExamMarks() {
        return examMarks;
    }

    public void setExamMarks(Set<Certificate> examMarks) {
        this.examMarks = examMarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getAverageCertificateMark() {
        return averageCertificateMark;
    }

    public void setAverageCertificateMark(int averageCertificateMark) {
        this.averageCertificateMark = averageCertificateMark;
    }


}
