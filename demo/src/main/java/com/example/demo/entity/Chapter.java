package com.example.demo.entity;

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
@Table(name="Chapter")
public class Chapter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ChapterID")
	private int chapterid;
	@Column(name = "Chaptername")
	private String chaptername;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="SubjectID")
	private Subject subject;
	
	public Chapter() {
		
	}
	
	public Chapter(String chaptername, Subject subject) {
		this.chaptername = chaptername;
		this.subject = subject;
	}
	

	public int getChapterid() {
		return chapterid;
	}

	public void setChapterid(int chapterid) {
		this.chapterid = chapterid;
	}

	public String getChaptername() {
		return chaptername;
	}
	public void setChaptername(String chaptername) {
		this.chaptername = chaptername;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
