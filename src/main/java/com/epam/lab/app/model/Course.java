package com.epam.lab.app.model;

import com.epam.lab.app.annotations.Field;
import com.epam.lab.app.annotations.Table;

@Table
public class Course {
	@Field
	private int courseId;
	@Field
	private String courseName;
	@Field
	private String courseDescription;
	@Field
	private int specialityId;

	public Course() {

	}

	public Course(int courseId, String courseName, String courseDescription, int specialityId) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.specialityId = specialityId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public int getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}

}
