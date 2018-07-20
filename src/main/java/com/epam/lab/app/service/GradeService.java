package com.epam.lab.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.epam.lab.app.dao.GradeDao;
import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Grade;

public class GradeService {
	private GradeDao gradeDao;

	public GradeService() {
		gradeDao = new GradeDao();
	}

	public List<Grade> getAll() {
		return gradeDao.getAll();
	}

	public void update(Grade grade) {
		gradeDao.update(grade);
	}

	public void create(Grade grade) {
		gradeDao.create(grade);
	}

	public void deleteById(int studentId, int courseId) {
		gradeDao.deleteById(studentId, courseId);
	}

	public Grade readById(int studentId, int courseId) {
		return gradeDao.readById(studentId, courseId);
	}

	public void deleteAllStudentGrades(int studentId) {
		Connection connection = ConnectionFactory.getConnection();
		String deleteQuery = String.format("DELETE FROM `grade` WHERE `studentId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
