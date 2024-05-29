package com.example.demo.data;

import com.example.demo.entity.Mocktest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MocktestRepository extends JpaRepository<Mocktest, Integer> {
    @Query(value = "select* from mocktests\n" +
            "            where subjectid =:subjectid", nativeQuery = true)
    List<Mocktest> findMocktestBySubjectid(int subjectid);
}