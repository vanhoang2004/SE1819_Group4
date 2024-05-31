package com.example.demo.data;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query("SELECT t FROM Teacher t WHERE t.user.userId = :userId")
    Teacher findTeacherById(@Param("userId") int id);
}
