package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TakenMockTest;

public interface TakenMocktestRepository extends JpaRepository<TakenMockTest, Integer> {
	@Query(value="SELECT * FROM TakenMockTest WHERE MockTestID = :mocktestid AND UserID =:studentid", nativeQuery = true)
	public TakenMockTest findByMocktestAndStudent(int mocktestid, int studentid);
}
