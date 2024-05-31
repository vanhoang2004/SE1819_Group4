package com.example.demo.data;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassRepository extends JpaRepository<Class,Integer> {
    @Query("SELECT c FROM Class c WHERE c.classCode = :classCode")
    Class findClassById(@Param("classCode") int code);
}
