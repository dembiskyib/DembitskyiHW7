package com.epam.lab.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.epam.lab.app.dao.StudentDao;
import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Student;

public class StudentService {
	private StudentDao studentDao;

	public StudentService() {
		studentDao = new StudentDao();
	}

	public List<Student> getAll() {
		return studentDao.getAll();
	}

	public void update(Student student) {
		studentDao.update(student);
	}

	public void create(Student student) {
		studentDao.create(student);
	}

	public void deleteById(int studentId) {
		studentDao.deleteById(studentId);
	}

	public Student readById(int studentId) {
		return studentDao.readById(studentId);
	}

	public void increaseStudentYear() {
		Connection connection = ConnectionFactory.getConnection();
		String updateQuery = String.format("UPDATE `student` SET `year` = `year`+1");
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(updateQuery);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
