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
@Table(name ="Teachermaterials")
public class TeacherMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Teachermaterialid")
	private int id;
	@Column(name = "Title")
	private String title;
	@Column(name = "Content")
	private String content;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="Classcode")
	private Class cla;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "Subjectid")
	private Subject subject;
	
	public TeacherMaterial() {
	
	}
	public TeacherMaterial( String title, String content, Class cla, Subject subject) {
		

		this.title = title;
		this.content = content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
