package com.example.demo.data;

import com.example.demo.entity.Chapters;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users Where Username = :name",nativeQuery = true)
    User findUserByName(@Param("name") String name);
}
