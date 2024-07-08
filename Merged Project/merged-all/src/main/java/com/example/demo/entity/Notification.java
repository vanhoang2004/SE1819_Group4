package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name="Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NotificationID")
    private Integer notificationId;

    @Column(name="Title")
    private String title;

    @Column(name="Message")
    private String message;

    @Column(name="Type")
    private String type;

    @Column(name="Createdat")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Classcode")
    private Class nclass;

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                ", nclass=" + nclass +
                '}';
    }

    public Class getNclass() {
        return nclass;
    }

    public void setNclass(Class nclass) {
        this.nclass = nclass;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Notification() {
    }

    public String getTimeDifference() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(this.createdAt, now);

        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            if(minutes > 1) return minutes + " minutes ago";
            else return minutes + " minute ago";
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            if(hours > 1) return hours + " hours ago";
            else return hours + " hour ago";
        } else if (seconds < (86400*30)){
            long days = seconds / 86400;
            if(days > 1) return days + " days ago";
            else return days + " day ago";
        } else{
            long weeks = seconds / (86400*7);
            if(weeks > 1) return weeks + " weeks ago";
            else return weeks + " week ago";
        }
    }

    public boolean isRecent() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(this.createdAt, now);

        long seconds = duration.getSeconds();

        return seconds <= 86400 * 3;
    }

    public boolean isOld(){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(this.createdAt, now);

        long seconds = duration.getSeconds();

        return seconds > 86400 * 3;
    }
}
