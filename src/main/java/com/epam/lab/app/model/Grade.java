package com.epam.lab.app.model;

import java.sql.Date;

import com.epam.lab.app.annotations.Field;
import com.epam.lab.app.annotations.Table;

@Table
public class Grade {
	@Field
	private int studentId;
	@Field
	private int courseId;
	@Field
	private Date examDate;
	@Field
	private int grade;

	public Grade() {

	}

	public Grade(int studentId, int courseId, Date examDate, int grade) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.examDate = examDate;
		this.grade = grade;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
