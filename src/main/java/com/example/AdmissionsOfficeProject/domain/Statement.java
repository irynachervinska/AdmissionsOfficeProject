package com.example.AdmissionsOfficeProject.domain;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "statements")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @Column(name = "average_certificate_mark")
    private int averageCertificateMark;
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "average_subject_mark")
//    @MapKeyColumn(name = "subject_id")
//    private Map<Subject, Integer> averageSubjectMark;
    private boolean accepted;

    public Statement() {
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

//    public Map<Subject, Integer> getAverageSubjectMark() {
//        return averageSubjectMark;
//    }
//
//    public void setAverageSubjectMark(Map<Subject, Integer> averageSubjectMark) {
//        this.averageSubjectMark = averageSubjectMark;
//    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
