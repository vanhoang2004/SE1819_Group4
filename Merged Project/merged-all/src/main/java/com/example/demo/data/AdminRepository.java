package com.example.demo.data;

import com.example.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    @Query("SELECT a FROM Admin a WHERE a.user.userId = :userId")
    Admin findAdminById(@Param("userId") int id);

    @Query("SELECT a FROM Admin a WHERE a.user.username LIKE :search% OR a.user.fullname LIKE :search% OR a.user.useremail LIKE :search%")
    List<Admin> search(@Param("search") String search);
}
