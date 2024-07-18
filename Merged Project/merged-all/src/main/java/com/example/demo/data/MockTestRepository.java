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
    @Query(value = "SELECT m.MockTestID,m.MockTestTitle,m.SubjectID,m.Start,m.End FROM mocktests m " +
            "INNER JOIN managers mgr ON m.SubjectID = mgr.SubjectID " +
            "WHERE mgr.UserID = :userId ORDER BY m.MockTestID DESC", nativeQuery = true)
    List<MockTest> findMockTestByUserIdOrder(int userId);


}
