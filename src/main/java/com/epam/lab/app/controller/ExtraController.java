package com.epam.lab.app.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.model.Course;
import com.epam.lab.app.service.CourseService;
import com.epam.lab.app.service.GradeService;
import com.epam.lab.app.service.SpecialityService;
import com.epam.lab.app.service.StudentService;

public class ExtraController {
	private static final Logger logger = LogManager.getLogger(ExtraController.class);
	private Scanner scanner;
	private CourseService courseService;
	private GradeService gradeService;
	private SpecialityService specialityService;
	private StudentService studentService;

	public ExtraController() {
		courseService = new CourseService();
		gradeService = new GradeService();
		specialityService = new SpecialityService();
		studentService = new StudentService();
		scanner = new Scanner(System.in);
	}

	public void printCourses() {
		logger.info("Print specialityId");
		List<Course> courseList = courseService.getAllCoursesBySpeciality(scanner.nextInt());
		logger.info(String.format("%10s %70s %20s", "courseId", "courseName", "specialityId"));
		courseList.forEach(course -> logger.info(String.format("%10s %70s %20d", course.getCourseId(),
				course.getCourseName(), course.getSpecialityId())));
	}

	public void deleteAllStudentGrades() {
		logger.info("Print studentId");
		gradeService.deleteAllStudentGrades(scanner.nextInt());
	}

	public void deleteWithTransfer() {
		logger.info("Print speciality to delete");
		specialityService.deleteWithTransfer(scanner.nextInt());
	}

	public void increaseYear() {
		studentService.increaseStudentYear();
		logger.info("Year increased");
	}
}
