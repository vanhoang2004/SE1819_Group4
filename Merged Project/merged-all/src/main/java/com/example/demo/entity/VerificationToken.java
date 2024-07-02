package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name ="verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "new_email")
    private String newEmail;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "userid")
    private User user;

    public VerificationToken() {

    }

    public VerificationToken(String token, User user, String newEmail) {
        this.token = token;
        this.user = user;
        this.newEmail = newEmail;
    }

    public int getId() {
        return id;
    }

    public void Id(int userid) {
        this.id = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
