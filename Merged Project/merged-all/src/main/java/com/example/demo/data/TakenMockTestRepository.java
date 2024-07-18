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

    @Query(value="SELECT * FROM TakenMockTest WHERE MockTestID = :mocktestid AND UserID =:studentid", nativeQuery = true)
    TakenMockTest findByMockTestAndStudent(int mocktestid, int studentid);

    //score list according to class
    @Query(value="select tm.takenmocktestid, tm.mocktestid, tm.userid, tm.score, tm.starttime, tm.endtime\n" +
            "from takenmocktest tm\n" +
            "join students s on s.userid = tm.userid\n" +
            "where s.classcode =:classcode and tm.mocktestid =:mocktestid;", nativeQuery = true)
    List<TakenMockTest> listScore(int classcode, int mocktestid);

    @Query(value="SELECT t.MockTestID, t.UserID, t.score, t.starttime, t.endtime, t.takenmocktestid FROM demo1.takenmocktest AS t JOIN demo1.students AS s \r\n"
            + "ON t.UserID = s.UserID WHERE s.Classcode = :classcode AND t.MockTestID =:mocktestid", nativeQuery = true)
    List<TakenMockTest> getTakenMockTestByMockTestAndClass(int classcode, int mocktestid);

    @Query(value="SELECT t.MockTestID, t.UserID, t.score, t.starttime, t.endtime, t.takenmocktestid FROM demo1.takenmocktest AS t JOIN demo1.students AS s \r\n"
            + "ON t.UserID = s.UserID WHERE t.MockTestID =:mocktestid", nativeQuery = true)
    List<TakenMockTest> getTakenMockTestByMockTest(int mocktestid);
}
