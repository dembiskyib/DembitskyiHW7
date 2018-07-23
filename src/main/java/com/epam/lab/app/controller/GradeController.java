package com.epam.lab.app.controller;

import java.sql.Date;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.model.Grade;
import com.epam.lab.app.service.GradeService;
import com.epam.lab.app.util.DateFormatter;

public class GradeController implements TableController {
	private static final Logger logger = LogManager.getLogger(GradeController.class);
	private GradeService gradeService;
	private Scanner scanner;

	public GradeController() {
		gradeService = new GradeService();
		scanner = new Scanner(System.in);
	}

	public void create() {
		logger.info("Print studentId, courseId, examDate and grade");
		int studentId = scanner.nextInt();
		int course = scanner.nextInt();
		String date = scanner.next();
		Date sqlDate = DateFormatter.format(date);
		int gradeColumn = scanner.nextInt();
		Grade grade = new Grade(studentId, course, sqlDate, gradeColumn);
		gradeService.create(grade);
	}

	public void read() {
		logger.info("Grades:");
		logger.info(String.format("%30s %30s %30s %30s", "studentId", "courseId", "examDate", "grade"));
		gradeService.getAll().forEach(grade -> logger.info(String.format("%30s %30s %30s %30s", grade.getStudentId(),
				grade.getCourseId(), grade.getExamDate(), grade.getGrade())));
	}

	public void update() {
		read();
		logger.info("Print `studentId` and `courseId` to update");
		Grade grade = gradeService.readById(scanner.nextInt(), scanner.nextInt());
		logger.info("Print\n 1 - to update `examDate`\n 2 - to update `grade`");
		switch (scanner.nextInt()) {
		case 1:
			logger.info("Print new examDate in format yyyy-MM-dd");
			String date = scanner.next();
			grade.setExamDate(DateFormatter.format(date));
			break;
		case 2:
			logger.info("Print new grade");
			grade.setGrade(scanner.nextInt());
			break;
		default:
			logger.info("Wrong number");
			break;
		}
		gradeService.update(grade);
	}

	public void delete() {
		logger.info("Print `studentId` and `courseId` to delete");
		gradeService.deleteById(scanner.nextInt(), scanner.nextInt());
	}
}
