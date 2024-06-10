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
@Table(name="Questions")

public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="Questionid")
	private int id;
	@Column(name ="Questiontitle")
	private String title;
	@Column(name ="image")
	private String image;
	@Column(name ="option1")
	private String option1;
	@Column(name ="option2")
	private String option2;
	@Column(name ="option3")
	private String option3;
	@Column(name ="option4")
	private String option4;
	@Column(name ="answer")
	private String answer;
//	 @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//	            CascadeType.DETACH, CascadeType.REFRESH})
	@Column(name ="subjectid")
	private String subjectid;
//	 @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//	            CascadeType.DETACH, CascadeType.REFRESH})
	@Column(name ="chapterid")
	private String chapterid;
//	 @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//	            CascadeType.DETACH, CascadeType.REFRESH})
	@Column(name ="levelid")
	private String levelid;
	@Column(name ="status")
	private int status;
	public Question() {
		
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getChapterid() {
		return chapterid;
	}
	public void setChapterid(String chapterid) {
		this.chapterid = chapterid;
	}
	public String getLevelid() {
		return levelid;
	}
	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	

}
