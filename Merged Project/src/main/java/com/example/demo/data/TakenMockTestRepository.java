package com.example.demo.data;

import com.example.demo.entity.TakenMockTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TakenMockTestRepository extends JpaRepository<TakenMockTest, Integer> {
    @Query(value = "SELECT * FROM TakenMockTest Where MockTestID = :id",nativeQuery = true)
        List<TakenMockTest> takenTestByMockTestID(Integer id);
    @Query(value = "SELECT * FROM TakenMockTest WHERE MockTestID = :id AND score BETWEEN :min AND :max", nativeQuery = true)
    List<TakenMockTest> takenTestByScore(Integer id, Integer min,Integer max);
    @Query(value = "SELECT t.takenmocktestid, t.mocktestid,t.userid,t.score\n" +
            "FROM TakenMockTest t\n" +
            "JOIN Users u ON t.UserID = u.UserID\n" +
            "WHERE t.MockTestID = :id ;\n",nativeQuery = true)
    List<TakenMockTest> takenTestByMockTestID2(Integer id);
}
