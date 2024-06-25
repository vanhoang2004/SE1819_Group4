package com.example.demo.data;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query("SELECT n FROM Notification n LEFT JOIN n.nclass c WHERE (c.classCode = :classCode OR c.classCode IS NULL) AND n.createdAt <= CURRENT_TIMESTAMP")
    List<Notification> findAllByClassCode(@Param("classCode") Integer classCode);

    @Query("SELECT n FROM Notification n WHERE n.type = 'general' AND n.createdAt <= CURRENT_TIMESTAMP")
    List<Notification> findAllGeneral();

    @Query("SELECT n FROM Notification n JOIN n.nclass c WHERE c.classCode = :classCode AND n.createdAt <= CURRENT_TIMESTAMP")
    List<Notification> findByClassCode(@Param("classCode") Integer classCode);
}
