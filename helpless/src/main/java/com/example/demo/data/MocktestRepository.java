package com.example.demo.data;

import com.example.demo.entity.Mocktest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MocktestRepository extends JpaRepository<Mocktest, Integer> {
    //list all mocktest in class
    @Query(value = "select* from mocktests\n" +
            "            where subjectid =:subjectid", nativeQuery = true)
    List<Mocktest> findMocktestBySubjectid(int subjectid);

    //filter by title
    @Query(value =
            "select * from mocktests\n" +
                    "where mocktesttitle like  %:filter% and subjectid =:subjectid" , nativeQuery = true)
    List<Mocktest> filterMocktest(Integer subjectid, @Param("filter") String filter);

    //find by title
    @Query(value =
            "select *\n" +
                    "from mocktests m\n" +
                    "where m.subjectid =:subjectid and m.mocktesttitle like %:keyword%", nativeQuery = true)
    List<Mocktest> searchMocktest(Integer subjectid, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM MockTests WHERE SubjectID = :subjectId", nativeQuery = true)
    List<Mocktest> findMockTestBySubjectId(@Param("subjectId") int subjectId);

    @Query(value = "SELECT * FROM MockTests WHERE MockTestID = :id", nativeQuery = true)
    Mocktest getMockTestById(int id);



}