package com.example.AdmissionsOfficeProject.domain;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "subjects")
//    @MapKeyColumn(name = "subject_id")
//    private Map<Subject, Integer> subjects;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Certificate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Map<Subject, Integer> getSubjects() {
//        return subjects;
//    }
//
//    public void setSubjects(Map<Subject, Integer> subjects) {
//        this.subjects = subjects;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
