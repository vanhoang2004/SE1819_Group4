package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="Subjects")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SubjectID")
	private int subjectId;
	@Column(name="Subjectname")
	private String subjectName;
	
	public Subject() {
		
	}
	public Subject(String subjectName) {
		
		this.subjectName = subjectName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
