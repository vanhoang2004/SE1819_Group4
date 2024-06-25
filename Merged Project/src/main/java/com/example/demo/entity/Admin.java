package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Admins")
public class Admin {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "UserID", insertable = false, updatable = false)
    private User user;

    @Column(name="Title")
    private String title;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }
}
