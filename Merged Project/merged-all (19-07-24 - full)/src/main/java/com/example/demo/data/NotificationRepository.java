package com.example.demo.data;

import com.example.demo.entity.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query("SELECT n FROM Notification n LEFT JOIN n.nclass c WHERE (c.classCode = :classCode OR c.classCode IS NULL) AND n.createdAt <= CURRENT_TIMESTAMP ORDER BY n.createdAt DESC")
    List<Notification> findAllByClassCode(@Param("classCode") Integer classCode);

    @Query("SELECT n FROM Notification n WHERE n.type = 'general' AND n.createdAt <= CURRENT_TIMESTAMP ORDER BY n.createdAt DESC")
    List<Notification> findAllGeneral();

    @Query("SELECT n FROM Notification n JOIN n.nclass c WHERE c.classCode = :classCode AND n.createdAt <= CURRENT_TIMESTAMP ORDER BY n.createdAt DESC")
    List<Notification> findByClassCode(@Param("classCode") Integer classCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.createdAt < :cutoffTime")
    int deleteNotificationsOlderThan(LocalDateTime cutoffTime);
}
