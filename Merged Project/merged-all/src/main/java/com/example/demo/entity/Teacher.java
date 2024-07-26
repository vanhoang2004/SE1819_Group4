package com.example.demo.entity;

import com.example.demo.data.SubjectRepository;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name="Teachers")
public class Teacher {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name="UserID")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "SubjectID", referencedColumnName = "SubjectID")
    private Subject subject;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacherclass",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "Classcode")
    )
    private List<Class> classes;

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

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

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + userId +
                ", user=" + user +
                ", subject=" + subject +
                ", classes=" + classes +
                '}';
    }
    
    public static Teacher fromMap(Map<String, String> data, SubjectRepository sjr) {
        Teacher t = new Teacher();
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
        if (data.containsKey("SubjectID")) {
//            System.out.println("Value of Integer.valueOf((int) Double.parseDouble(data.get(\"SubjectID\"))) " + Integer.valueOf((int) Double.parseDouble(data.get("SubjectID"))));
            t.setSubject(sjr.findSubjectById(Integer.valueOf((int) Double.parseDouble(data.get("SubjectID")))));
        }
        t.getUser().setEnabled(true);
        t.getUser().setRole("Teacher");
        return t;
    }
}
