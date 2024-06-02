package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @Id
    @Column(name="UserID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;

    @Column(name="Username")
    private String username;

    @Column(name="Password")
    private String password;

    @Column(name="Fullname")
    private String fullname;

    @Column(name="Useremail")
    private String useremail;

    @Column(name="Role")
    private String role;

    @Column(name="Enabled")
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//no encryption
    public String getPassword() {
        if(password!=null && password.startsWith("{noop}")) return password.substring(6);
        return password;
    }

    public void setPassword(String password) {
        this.password = "{noop}" + password;
    }
//bcrypt encryption
//    public void setPassword(String password) {
//        this.password = "{bcrypt}" + password;
//    }

//    public String getPassword() {
//        if(password.startsWith("{bcrypt}")) return password.substring(6);
//        return password;
//    }

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

    public String getRole() {
        if(role !=null && role.startsWith("ROLE_")){
            String temp = role.substring(5).toLowerCase();
            char[] tempChars = temp.toCharArray();
            tempChars[0] = Character.toUpperCase(tempChars[0]);
            return String.valueOf(tempChars);
        }
        return role;
    }

    public void setRole(String role) {
        role = "ROLE_" + role;
        this.role = role.toUpperCase();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", useremail='" + useremail + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
