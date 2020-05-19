package com.example.AdmissionsOfficeProject.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_profile_photos")
public class UserPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String photo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserPhoto() {
    }

    public UserPhoto(String photo, User user) {
        this.photo = photo;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
