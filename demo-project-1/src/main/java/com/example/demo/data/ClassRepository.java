package com.example.demo.data;

import com.example.demo.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRepository extends JpaRepository<Classes,Integer> {
    @Query(value = "SELECT * FROM classes WHERE classcode = :code", nativeQuery = true)
    Classes findClassByCode(@Param("code") Integer code);

}
