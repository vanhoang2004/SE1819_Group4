package com.example.demo.data;

import com.example.demo.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Query(value="select LevelID from levels where LevelName like :name ",nativeQuery = true)
    Integer getLevelID(String name);


}
