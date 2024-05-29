package com.example.demo.data;

import com.example.demo.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Query(value = "select c.classcode, c.classname\n" +
            "from users u\n" +
            "join teacherclass tc on tc.userid = u.userid\n" +
            "join classes c on c.classcode = tc.classcode\n" +
            "where u.username =:username", nativeQuery = true)
    List<Class> findClassByUserId(String username);
}