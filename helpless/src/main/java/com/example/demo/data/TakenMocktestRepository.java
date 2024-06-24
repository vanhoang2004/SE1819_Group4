package com.example.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TakenMockTest;

import java.util.List;

public interface TakenMocktestRepository extends JpaRepository<TakenMockTest, Integer> {
	@Query(value="SELECT * FROM TakenMockTest WHERE MockTestID = :mocktestid AND UserID =:studentid", nativeQuery = true)
	public TakenMockTest findByMocktestAndStudent(int mocktestid, int studentid);

	//score list according to class
	@Query(value="select tm.takenmocktestid, tm.mocktestid, tm.userid, tm.score\n" +
			"from takenmocktest tm\n" +
			"join students s on s.userid = tm.userid\n" +
			"where s.classcode =:classcode and tm.mocktestid =:mocktestid;", nativeQuery = true)
	List<TakenMockTest> listScore(int classcode, int mocktestid);
}
