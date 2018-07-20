package com.epam.lab.app.dataBaseInteraction;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataBaseCreator {
	private static final Logger logger = LogManager.getLogger(DataBaseCreator.class);
	private Scanner scanner;
	private Connection connection;

	public DataBaseCreator() {
		scanner = new Scanner(System.in);
	}

	private void create() {
		try {
			Statement statement = connection.createStatement();
			String createDatabase = "CREATE DATABASE IF NOT EXISTS `university`";
			statement.executeUpdate(createDatabase);
			createTables();
			fillTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createTables() {
		String createCourse = "CREATE TABLE IF NOT EXISTS `course` "
				+ "(`courseId` int(5) NOT NULL DEFAULT '0',`courseName` char(100) DEFAULT NULL,"
				+ "`courseDescription` char(250) DEFAULT NULL,`specialityId` int(5) DEFAULT NULL, "
				+ "PRIMARY KEY (`courseId`), KEY `specialityId` (`specialityId`), CONSTRAINT `course_ibfk_2` "
				+ "FOREIGN KEY (`specialityId`) REFERENCES `speciality` (`specialityId`) ON DELETE CASCADE ) "
				+ "ENGINE=InnoDB DEFAULT CHARSET=utf8";
		String createGrade = "CREATE TABLE IF NOT EXISTS `grade` (`studentId` int(5) NOT NULL DEFAULT '0', `courseId` int(5) NOT NULL DEFAULT '0',"
				+ " `examDate` date DEFAULT NULL, `grade` int(3) DEFAULT NULL, PRIMARY KEY (`studentId`,`courseId`),"
				+ " KEY `courseId` (`courseId`), CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`),"
				+ " CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
		String createSpeciality = "CREATE TABLE IF NOT EXISTS `speciality` ( `specialityId` int(5) NOT NULL DEFAULT '0',"
				+ " `specialityName` char(50) DEFAULT NULL, `specialityDescription` char(255) DEFAULT NULL,"
				+ " PRIMARY KEY (`specialityId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
		String createStudent = "CREATE TABLE IF NOT EXISTS `student` (`studentId` int(5) NOT NULL DEFAULT '0',"
				+ " `secondName` char(25) DEFAULT NULL, `firstName` char(25) DEFAULT NULL,"
				+ " `patronymic` char(25) DEFAULT NULL, `motherFullName` char(100) DEFAULT NULL,"
				+ " `fatherFullName` char(100) DEFAULT NULL, `address` char(100) DEFAULT NULL, `phone` char(12) DEFAULT NULL,"
				+ " `passportData` char(100) DEFAULT NULL, `recordBookNumber` int(20) DEFAULT NULL, `startDate` date DEFAULT NULL,"
				+ " `group` char(10) DEFAULT NULL, `year` int(1) DEFAULT NULL, `specialityId` int(5) DEFAULT NULL,"
				+ " `onExtramural` tinyint(1) DEFAULT NULL, PRIMARY KEY (`studentId`), KEY `specialityId` (`specialityId`),"
				+ " CONSTRAINT `student_ibfk_1` FOREIGN KEY (`specialityId`) REFERENCES `speciality` (`specialityId`)) "
				+ "ENGINE=InnoDB DEFAULT CHARSET=utf8";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("use `university`");
			statement.executeUpdate(createSpeciality);
			statement.executeUpdate(createCourse);
			statement.executeUpdate(createStudent);
			statement.executeUpdate(createGrade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillTables() {
		fillSpeciality();
		fillCourse();
		fillStudent();
		fillGrade();
	}

	public void fillCourse() {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO `course` (`courseId`,`courseName`,`courseDescription`,`specialityId`) "
					+ "VALUES (103,'Mathematical Foundations of Computing','Mathematical foundations required for computer science, including propositional predicate logic, induction, sets, functions, and relations',50101)");
			statement.executeUpdate("INSERT INTO `course` (`courseId`,`courseName`,`courseDescription`,`specialityId`) "
					+ "VALUES (166,'Data Structures','Techniques in the design, analysis, and implementation of data structures.',50102)");
			statement.executeUpdate("INSERT INTO `course` (`courseId`,`courseName`,`courseDescription`,`specialityId`) "
					+ "VALUES (221,'Artificial Intelligence: Principles and Techniques','Artificial intelligence (AI) has had a huge impact in many areas, including medical diagnosis, speech recognition, robotics, web search, advertising, and scheduling.',50101)");
			statement.executeUpdate("INSERT INTO `course` (`courseId`,`courseName`,`courseDescription`,`specialityId`) "
					+ "VALUES (224,'Analysis of Networks','Networks are a fundamental tool for modeling complex social, technological, and biological systems.',50102)");
			statement.executeUpdate("INSERT INTO `course` (`courseId`,`courseName`,`courseDescription`,`specialityId`) "
					+ "VALUES (229,'Machine Learning','Topics: statistical pattern recognition, linear and non-linear regression, non-parametric methods, exponential family',50102)");
			statement.executeUpdate("INSERT INTO `course` (`courseId`,`courseName`,`courseDescription`,`specialityId`) "
					+ "VALUES (231,'Computer Vision','An introduction to the concepts and applications in computer vision.',50101)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillGrade() {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (1,103,'2017-05-22',95)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (1,221,'2017-05-24',89)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (1,231,'2017-05-27',87)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (2,166,'2018-04-12',78)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (2,224,'2017-04-30',98)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (2,229,'2017-04-24',77)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (3,103,'2016-06-01',57)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (3,221,'2016-06-10',78)");
			statement.executeUpdate(
					"INSERT INTO `grade` (`studentId`,`courseId`,`examDate`,`grade`) VALUES (3,231,'2016-06-03',86)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillSpeciality() {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO `speciality` (`specialityId`,`specialityName`,`specialityDescription`) VALUES (50101,'Computer-Aided Design','The study of Computer Science and Information Technology provides training of the highly qualified specialists in the field of programming, automated design, and complicated systems development.')");
			statement.executeUpdate(
					"INSERT INTO `speciality` (`specialityId`,`specialityName`,`specialityDescription`) VALUES (50102,'Information Systems and Networks','he reason of the department creation was the organizational and structural design of the new young scientific school on problems of databases, data and knowledge banks.')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillStudent() {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO `student` (`studentId`,`secondName`,`firstName`,`patronymic`,`motherFullName`,`fatherFullName`,"
							+ "`address`,`phone`,`passportData`,`recordBookNumber`,`startDate`,`group`,`year`,`specialityId`,`onExtramural`) "
							+ "VALUES (1,'Dembitskyi','Bohdan','Vasylyovich','Dembitska T. L.','Dembitskyi V. V.','Zolota 10','380968305409',"
							+ "'TA235465',32321424,'2014-09-06','KN-45',6,50101,0)");
			statement.executeUpdate(
					"INSERT INTO `student` (`studentId`,`secondName`,`firstName`,`patronymic`,`motherFullName`,`fatherFullName`,"
							+ "`address`,`phone`,`passportData`,`recordBookNumber`,`startDate`,`group`,`year`,`specialityId`,`onExtramural`) "
							+ "VALUES (2,'Steblak','Yurii','Iakovych','Steblak L. M.','Steblak I. O.','Slovak 33','380969175302','NA892301',32321425,"
							+ "'2015-09-06','KN-32',5,50102,0)");
			statement.executeUpdate(
					"INSERT INTO `student` (`studentId`,`secondName`,`firstName`,`patronymic`,`motherFullName`,`fatherFullName`,"
							+ "`address`,`phone`,`passportData`,`recordBookNumber`,`startDate`,`group`,`year`,`specialityId`,`onExtramural`)"
							+ " VALUES (3,'Chornyi','Andriy','Oleksandrovich','Chorna O. K.','Chronyi O. P.','Slovak 34','380932135842','SP213652',"
							+ "32321426,'2014-09-06','KN-44',6,50102,1)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void login() {
		logger.info("Print database port:");
		ConnectionFactory.portNumber = scanner.nextInt();
		logger.info("Print login:");
		ConnectionFactory.login = scanner.next();
		logger.info("Print password:");
		ConnectionFactory.password = scanner.next();
		connection = ConnectionFactory.getConnection();
		create();
	}
}
