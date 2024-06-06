package com.example.demo.data;

import com.example.demo.entity.Class;
import com.example.demo.entity.Mocktest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MocktestRepository extends JpaRepository<Mocktest, Integer> {
    @Query(value = "select* from mocktests\n" +
            "            where subjectid =:subjectid", nativeQuery = true)
    List<Mocktest> findMocktestBySubjectid(int subjectid);

    @Query(value =
            "select *\n" +
            "from mocktests m\n" +
            "where m.subjectid =:subjectid and m.mocktesttitle like %:keyword%", nativeQuery = true)
    List<Mocktest> searchMocktest(Integer subjectid, @Param("keyword") String keyword);
}