package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name="Users")
public class User {
    @Id
    @Column(name="UserID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="useremail")
    private String useremail;

    public User(int userId, String username, String password, String useremail) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.useremail = useremail;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
