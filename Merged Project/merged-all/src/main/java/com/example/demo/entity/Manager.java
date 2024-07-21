package com.example.demo.entity;

import com.example.demo.data.ManagerRepository;
import com.example.demo.data.SubjectRepository;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name="Managers")
public class Manager {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name="UserID")
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="SubjectID")
    private Subject subject;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Manager() {
    }

    @Override
    public String toString() {
        return "Manager{" +
                "userId=" + userId +
                ", user=" + user +
                ", subject=" + subject +
                '}';
    }
    
        public static Manager fromMap(Map<String, String> data, SubjectRepository sjr) {
        Manager ma = new Manager();
        ma.setUser(new User());

        // Kiểm tra và gán các giá trị từ Map
        if (data.containsKey("Username")) {
            ma.getUser().setUsername(data.get("Username"));
        }
        if (data.containsKey("Password")) {
            ma.getUser().setPassword(data.get("Password"));
        }
        if (data.containsKey("Fullname")) {
            ma.getUser().setFullname(data.get("Fullname"));
        }
        if (data.containsKey("Useremail")) {
            ma.getUser().setUseremail(data.get("Useremail"));
        }
        if (data.containsKey("SubjectID")) {
//            System.out.println("Value of Integer.valueOf((int) Double.parseDouble(data.get(\"SubjectID\"))) " + Integer.valueOf((int) Double.parseDouble(data.get("SubjectID"))));
            ma.setSubject(sjr.findSubjectById(Integer.valueOf((int) Double.parseDouble(data.get("SubjectID")))));
        }
        ma.getUser().setEnabled(true);
        ma.getUser().setRole("Manager");
        return ma;
    }
}
