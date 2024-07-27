package com.example.demo.entity;

import com.example.demo.data.ClassRepository;
import com.example.demo.data.SubjectRepository;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name="Students")
public class Student {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name="UserID")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="Classcode")
    private Class sclass;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Class getSclass() {
        return sclass;
    }

    public void setSclass(Class sclass) {
        this.sclass = sclass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "userId=" + userId +
                ", user=" + user +
                ", sclass=" + sclass +
                '}';
    }

    public static Student fromMap(Map<String, String> data, ClassRepository cr) {
        Student t = new Student();
        t.setUser(new User());

        // Kiểm tra và gán các giá trị từ Map
        if (data.containsKey("Username")) {
            t.getUser().setUsername(data.get("Username"));
        }
        if (data.containsKey("Password")) {
            t.getUser().setPassword(data.get("Password"));
        }
        if (data.containsKey("Fullname")) {
            t.getUser().setFullname(data.get("Fullname"));
        }
        if (data.containsKey("Useremail")) {
            t.getUser().setUseremail(data.get("Useremail"));
        }
        if (data.containsKey("Classcode")) {
//            System.out.println("Value of Integer.valueOf((int) Double.parseDouble(data.get(\"SubjectID\"))) " + Integer.valueOf((int) Double.parseDouble(data.get("SubjectID"))));
            t.setSclass(cr.findClassById(Integer.valueOf((int) Double.parseDouble(data.get("Classcode")))));
        }
        t.getUser().setEnabled(true);
        t.getUser().setRole("Student");
        return t;
    }
}