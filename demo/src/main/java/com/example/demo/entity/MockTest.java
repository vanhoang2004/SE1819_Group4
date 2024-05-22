package com.example.demo.entity;

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
@Table(name="Mocktests")
public class MockTest {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MocktestID", unique = true)
    private int mockTestId;

    @Column(name = "Mocktesttitle")
    private String mockTestTitle;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="SubjectID")
    private Subject subject;
    @Column(name="start")
    private LocalDateTime start;
    @Column(name="end")
    private LocalDateTime end;

    public MockTest() {}

    public MockTest(String mockTestTitle) {
        this.mockTestTitle = mockTestTitle;
    }
    

    public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public int getMockTestId() {
        return mockTestId;
    }

    public void setMockTestId(int mockTestId) {
        this.mockTestId = mockTestId;
    }

    public String getMockTestTitle() {
        return mockTestTitle;
    }

    public void setMockTestTitle(String mockTestTitle) {
        this.mockTestTitle = mockTestTitle;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}