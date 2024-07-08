package com.example.demo.data;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    User findUserById(@Param("userId") int id);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.username like :search% or u.fullname like %:search% or u.useremail like :search%")
    List<User> search(@Param("search") String search);

    User findByUserId(Integer id);

    User findByUsername(String username);

    User findByUseremail(String email);
}
