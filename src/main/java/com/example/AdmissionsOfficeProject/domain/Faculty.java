package com.example.AdmissionsOfficeProject.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(name = "places_number_paid")
    private int placesNumberPaid;
    @Column(name = "places_number_free")
    private int placesNumberFree;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "faculty_subject_mapping",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<Subject> subjects;

    public Faculty() {
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

    public int getPlacesNumberPaid() {
        return placesNumberPaid;
    }

    public void setPlacesNumberPaid(int placesNumberPaid) {
        this.placesNumberPaid = placesNumberPaid;
    }

    public int getPlacesNumberFree() {
        return placesNumberFree;
    }

    public void setPlacesNumberFree(int placesNumberFree) {
        this.placesNumberFree = placesNumberFree;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public boolean hasSubject(Subject subject) {
        for (Subject subject1: getSubjects()) {
            if (subject1.getId() == subject.getId()) {
                return true;
            }
        }
        return false;
    }
}
