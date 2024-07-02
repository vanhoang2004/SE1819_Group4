package com.example.demo.data;

import com.example.demo.entity.MockTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MockTestRepository extends JpaRepository<MockTest, Integer> {
    @Query(value = "SELECT * FROM mocktests Where MockTestID= :mocktestid",nativeQuery = true)
    MockTest mocktestbyID(Integer  mocktestid);

    @Query(value = "SELECT m.MockTestID,m.MockTestTitle,m.SubjectID,m.Start,m.End FROM mocktests m " +
            "INNER JOIN managers mgr ON m.SubjectID = mgr.SubjectID " +
            "WHERE mgr.UserID = :userId", nativeQuery = true)
    List<MockTest> findMockTestByUserId(int userId);

    @Query(value = "SELECT m.MockTestID,m.MockTestTitle,m.SubjectID,m.Start,m.End FROM mocktests m " +
            "INNER JOIN managers mgr ON m.SubjectID = mgr.SubjectID " +
            "WHERE mgr.UserID = :userId and m.MockTestTitle LIKE %:keyword%", nativeQuery = true)
    List<MockTest> searchMockTest(Integer userId,@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM MockTests WHERE SubjectID = :subjectId", nativeQuery = true)
    List<MockTest> findMockTestBySubjectId(@Param("subjectId") int subjectId);

    @Query(value = "SELECT * FROM MockTests WHERE MockTestID = :id", nativeQuery = true)
    MockTest getMockTestById(int id);

    //list all mocktest in class
    @Query(value = "select* from mocktests\n" +
            "            where subjectid =:subjectid", nativeQuery = true)
    List<MockTest> findMockTestBySubjectid(int subjectid);

    //filter by title
    @Query(value =
            "select * from mocktests\n" +
                    "where mocktesttitle like  %:filter% and subjectid =:subjectid" , nativeQuery = true)
    List<MockTest> filterMockTest(Integer subjectid, @Param("filter") String filter);
}
