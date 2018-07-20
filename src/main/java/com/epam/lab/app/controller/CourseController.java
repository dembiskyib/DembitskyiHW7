package com.epam.lab.app.controller;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.model.Course;
import com.epam.lab.app.service.CourseService;

public class CourseController implements TableController {
	private static final Logger logger = LogManager.getLogger(CourseController.class);
	CourseService courseService;
	Scanner scanner;

	public CourseController() {
		courseService = new CourseService();
		scanner = new Scanner(System.in);
	}

	public void create() {
		logger.info("Print courseId, courseName, courseDescription and specialityId");
		Course course = new Course(scanner.nextInt(), scanner.next(), scanner.next(), scanner.nextInt());
		courseService.create(course);
	}

	public void read() {
		logger.info("Courses:");
		logger.info(String.format("%10s %70s %20s", "courseId", "courseName", "specialityId"));
		courseService.getAll().forEach(course -> logger.info(String.format("%10s %70s %20d", course.getCourseId(),
				course.getCourseName(), course.getSpecialityId())));
	}

	public void update() {
		read();
		logger.info("Print courseId to update");
		Course course = courseService.readById(scanner.nextInt());
		logger.info(
				"Print\n 1 - to update `courseName`\n 2 - to update `courseDescription`\n 3 - to update `specialityId`");
		switch (scanner.nextInt()) {
		case 1:
			logger.info("Print new name");
			course.setCourseName(scanner.next());
			break;
		case 2:
			logger.info("Print new description");
			course.setCourseDescription(scanner.next());
			break;
		case 3:
			logger.info("Print new specialityId");
			course.setSpecialityId(scanner.nextInt());
			break;
		default:
			logger.info("Wrong number");
			break;
		}
		courseService.update(course);
	}

	public void delete() {
		logger.info("Print courseId to delete");
		courseService.deleteById(scanner.nextInt());
	}
}
