package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @Id
    @Column(name="UserID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;

    @Column(name="Username")
    private String username;

    @Column(name="Password")
    private String password;

    @Column(name="Fullname")
    private String fullname;

    @Column(name="Useremail")
    private String useremail;

    public User(String username, String password, String fullname, String useremail) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
