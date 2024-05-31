package com.example.demo.data;

import com.example.demo.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    @Query("SELECT m FROM Manager m WHERE m.user.userId = :userId")
    Manager findManagerById(@Param("userId") int id);
}
