package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="TeacherPractice")
public class TeacherPractice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Teacherpracticeid")
	private int id;
	@Column(name="Title")
	private String title;
	@Column(name="Publishdate")
	private LocalDateTime publishDate;
	@Column(name="deadline")
	private LocalDate deadline;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="Classcode")
	private Class cla;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "Subjectid")
	private Subject subject;
	
	public TeacherPractice() {
		
	}
	public TeacherPractice(String title, LocalDateTime publishDate, LocalDate deadline, Class cla,
			Subject subject) {
		
		this.title = title;
		this.publishDate = publishDate;
		this.deadline = deadline;
		this.cla = cla;
		this.subject = subject;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDateTime publishDate) {
		this.publishDate = publishDate;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public Class getCla() {
		return cla;
	}
	public void setCla(Class cla) {
		this.cla = cla;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
}
