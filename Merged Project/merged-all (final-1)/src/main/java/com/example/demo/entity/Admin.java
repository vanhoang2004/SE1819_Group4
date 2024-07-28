package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Map;

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
        this.user = new User();
        this.user.setEnabled(true);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }
    
    public static Admin fromMap(Map<String, String> data) {
        Admin a = new Admin();
        a.setUser(new User());

        // Kiểm tra và gán các giá trị từ Map
        if (data.containsKey("Username")) {
            a.getUser().setUsername(data.get("Username"));
        }
        if (data.containsKey("Password")) {
            a.getUser().setPassword(data.get("Password"));
        }
        if (data.containsKey("Fullname")) {
            a.getUser().setFullname(data.get("Fullname"));
        }
        if (data.containsKey("Useremail")) {
            a.getUser().setUseremail(data.get("Useremail"));
        }
        if (data.containsKey("Title")) {
            a.setTitle(data.get("Title"));
        }
        a.getUser().setEnabled(true);
        a.getUser().setRole("Admin");
        return a;
    }
    
}
