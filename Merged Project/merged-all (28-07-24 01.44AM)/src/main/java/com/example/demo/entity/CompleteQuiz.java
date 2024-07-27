package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="completequiz")
public class CompleteQuiz {   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="userid")
	private Student student;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="chapterid")
	private Chapter chapter;
	@Column(name="score")
	private float score;
	@Column(name="timecomplete")
	private LocalDate timeComplete;
	public CompleteQuiz() {
		
	}
	public CompleteQuiz(Student student, Chapter chapter, float score, LocalDate timeComplete) {
		super();
		this.student = student;
		this.chapter = chapter;
		this.score = score;
		this.timeComplete = timeComplete;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public LocalDate getTimeComplete() {
		return timeComplete;
	}
	public void setTimeComplete(LocalDate timeComplete) {
		this.timeComplete = timeComplete;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
