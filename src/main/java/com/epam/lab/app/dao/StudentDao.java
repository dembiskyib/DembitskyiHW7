package com.epam.lab.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Student;
import com.epam.lab.app.transformer.StudentTransformer;

public class StudentDao {
	StudentTransformer studentTransformer;

	public StudentDao() {
		studentTransformer = new StudentTransformer();
	}

	public List<Student> getAll() {
		Connection connection = ConnectionFactory.getConnection();
		List<Student> studentList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			studentList = studentTransformer.transformAll(statement.executeQuery("SELECT * FROM `student`"));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
	}

	public void update(Student student) {
		Connection connection = ConnectionFactory.getConnection();
		String updateQuery = String
				.format("UPDATE `student` SET `secondName` = ?, `firstName` = ?, `patronymic` = ?,`motherFullName` = ?,`fatherFullName` = ?, "
						+ "`address` = ?, `phone` = ?, `passportData` = ?, `recordBookNumber` = ?, `startDate` = ?, `group` = ?, "
						+ "`year` = ?, `specialityId` = ?, `onExtramural` = ? WHERE `studentId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, student.getSecondName());
			preparedStatement.setString(2, student.getFirstName());
			preparedStatement.setString(3, student.getPatronymic());
			preparedStatement.setString(4, student.getMotherFullName());
			preparedStatement.setString(5, student.getFatherFullName());
			preparedStatement.setString(6, student.getAddress());
			preparedStatement.setString(7, student.getPhone());
			preparedStatement.setString(8, student.getPassportData());
			preparedStatement.setInt(9, student.getRecordBookNumber());
			preparedStatement.setDate(10, student.getStartDate());
			preparedStatement.setString(11, student.getGroup());
			preparedStatement.setInt(12, student.getYear());
			preparedStatement.setInt(13, student.getSpecialityId());
			preparedStatement.setBoolean(14, student.isOnExtramural());
			preparedStatement.setInt(15, student.getStudentId());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(Student student) {
		Connection connection = ConnectionFactory.getConnection();
		String createQuery = String
				.format("INSERT INTO `student` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
			preparedStatement.setInt(1, student.getStudentId());
			preparedStatement.setString(2, student.getSecondName());
			preparedStatement.setString(3, student.getFirstName());
			preparedStatement.setString(4, student.getPatronymic());
			preparedStatement.setString(5, student.getMotherFullName());
			preparedStatement.setString(6, student.getFatherFullName());
			preparedStatement.setString(7, student.getAddress());
			preparedStatement.setString(8, student.getPhone());
			preparedStatement.setString(9, student.getPassportData());
			preparedStatement.setInt(10, student.getRecordBookNumber());
			preparedStatement.setDate(11, student.getStartDate());
			preparedStatement.setString(12, student.getGroup());
			preparedStatement.setInt(13, student.getYear());
			preparedStatement.setInt(14, student.getSpecialityId());
			preparedStatement.setBoolean(15, student.isOnExtramural());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(int studentId) {
		Connection connection = ConnectionFactory.getConnection();
		String deleteQuery = String.format("DELETE FROM `student` WHERE `studentId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Student readById(int studentId) {
		Connection connection = ConnectionFactory.getConnection();
		String readQuery = String.format("SELECT * FROM `student` WHERE `studentId` = ?");
		Student student = new Student();
		try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)) {
			preparedStatement.setInt(1, studentId);
			student = studentTransformer.transformAll(preparedStatement.executeQuery()).get(0);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
}
