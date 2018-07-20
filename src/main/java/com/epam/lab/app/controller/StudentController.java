package com.epam.lab.app.controller;

import java.sql.Date;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.model.Student;
import com.epam.lab.app.service.StudentService;
import com.epam.lab.app.util.DateFormatter;

public class StudentController implements TableController {
	private static final Logger logger = LogManager.getLogger(SpecialityController.class);
	StudentService studentService;
	Scanner scanner;

	public StudentController() {
		studentService = new StudentService();
		scanner = new Scanner(System.in);
	}

	public void create() {
		logger.info("Print studentId");
		int studentId = scanner.nextInt();
		logger.info("Print secondName");
		String secondName = scanner.next();
		logger.info("Print firstName");
		String firstName = scanner.next();
		logger.info("Print patronymic");
		String patronymic = scanner.next();
		logger.info("Print motherFullName");
		String motherFullName = scanner.next();
		logger.info("Print fatherFullName");
		String fatherFullName = scanner.next();
		logger.info("Print address");
		String address = scanner.next();
		logger.info("Print phone");
		String phone = scanner.next();
		logger.info("Print passportData");
		String passportData = scanner.next();
		logger.info("Print recordBookNumber");
		int recordBookNumber = scanner.nextInt();
		logger.info("Print startDate (YYYY-MM-DD)");
		Date startDate = DateFormatter.format(scanner.next());
		logger.info("Print group");
		String group = scanner.next();
		logger.info("Print year");
		int year = scanner.nextInt();
		logger.info("Print specialityId");
		int specialityId = scanner.nextInt();
		logger.info("Print is on extramural");
		boolean onExtramural = scanner.nextBoolean();
		Student student = new Student(studentId, secondName, firstName, patronymic, motherFullName, fatherFullName,
				address, phone, passportData, recordBookNumber, startDate, group, year, specialityId, onExtramural);
		studentService.create(student);
	}

	public void read() {
		logger.info("Students:");
		logger.info(String.format("%30s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s",
				"studentId", "secondName", "firstName", "patronymic", "motherFullName", "fatherFullName", "address",
				"phone", "passportData", "recordBookNumber", "startDate", "group", "year", "specialityId",
				"onExtramural"));
		studentService.getAll()
				.forEach(student -> logger.info(
						String.format("%30d %30s %30s %30s %30s %30s %30s %30s %30s %30d %30s %30s %30d %30d %30b",
								student.getStudentId(), student.getSecondName(), student.getFirstName(),
								student.getPatronymic(), student.getMotherFullName(), student.getFatherFullName(),
								student.getAddress(), student.getPhone(), student.getPassportData(),
								student.getRecordBookNumber(), student.getStartDate(), student.getGroup(),
								student.getYear(), student.getSpecialityId(), student.isOnExtramural())));
	}

	public void update() {
		read();
		logger.info("Print `studentId` to update");
		Student student = studentService.readById(scanner.nextInt());
		logger.info("Print\n 1 - to update `secondName`\n 2 - to update `firstName`\n "
				+ "3 - to update `patronymic`\n 4 - to update `motherFullName`\n "
				+ "5 - to update `fatherFullName`\n 6 - to update `address`\n "
				+ "7 - to update `phone`\n 8 - to update `passportData`\n "
				+ "9 - to update `recordBookNumber`\n 10 - to update `startDate`\n "
				+ "11 - to update `group`\n 12 - to update `year`\n "
				+ "13 - to update `specialityId`\n 14 - to update `onExtramural`\n");
		switch (scanner.nextInt()) {
		case 1:
			logger.info("Print new secondName");
			student.setSecondName(scanner.next());
			break;
		case 2:
			logger.info("Print new firstName");
			student.setFirstName(scanner.next());
			break;
		case 3:
			logger.info("Print new patronymic");
			student.setPatronymic(scanner.next());
			break;
		case 4:
			logger.info("Print new motherFullName");
			student.setMotherFullName(scanner.next());
			break;
		case 5:
			logger.info("Print new fatherFullName");
			student.setFatherFullName(scanner.next());
			break;
		case 6:
			logger.info("Print new address");
			student.setAddress(scanner.next());
			break;
		case 7:
			logger.info("Print new phone");
			student.setPhone(scanner.next());
			break;
		case 8:
			logger.info("Print new passportData");
			student.setPassportData(scanner.next());
			break;
		case 9:
			logger.info("Print new recordBookNumber");
			student.setRecordBookNumber(scanner.nextInt());
			break;
		case 10:
			logger.info("Print new startDate (YYYY-MM-DD)");
			student.setStartDate(DateFormatter.format(scanner.next()));
			break;
		case 11:
			logger.info("Print new group");
			student.setGroup(scanner.next());
			break;
		case 12:
			logger.info("Print new year");
			student.setYear(scanner.nextInt());
			break;
		case 13:
			logger.info("Print new specialityId");
			student.setSpecialityId(scanner.nextInt());
			break;
		case 14:
			logger.info("Print new onExtramural");
			student.setOnExtramural(scanner.nextBoolean());
			break;
		default:
			logger.info("Wrong number");
			break;
		}
		studentService.update(student);
	}

	public void delete() {
		read();
		logger.info("Print `studentId` to delete");
		studentService.deleteById(scanner.nextInt());
	}
}
