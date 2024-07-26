package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="takenmocktest")
public class TakenMockTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "takenmocktestid")
	private int id;
	@OneToOne
	@JoinColumn(name = "mocktestid", referencedColumnName = "mocktestid")
	private MockTest mocktest;

	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private Student student;
	@Column(name = "score")
	private Float score;
	@Column(name="starttime")
	private LocalDateTime starttime;
	@Column(name="endtime")
	private LocalDateTime endtime;

	public TakenMockTest(MockTest mocktest, Student student, Float score, LocalDateTime starttime,
                         LocalDateTime endtime) {

		this.mocktest = mocktest;
		this.student = student;
		this.score = score;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	public TakenMockTest() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MockTest getMockTest() {
		return mocktest;
	}
	public void setMockTest(MockTest mocktest) {
		this.mocktest = mocktest;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public LocalDateTime getStarttime() {
		return starttime;
	}
	public void setStarttime(LocalDateTime starttime) {
		this.starttime = starttime;
	}
	public LocalDateTime getEndtime() {
		return endtime;
	}
	public void setEndtime(LocalDateTime endtime) {
		this.endtime = endtime;
	}

}