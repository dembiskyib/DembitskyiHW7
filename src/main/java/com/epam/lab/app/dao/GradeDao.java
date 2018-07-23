package com.epam.lab.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Grade;
import com.epam.lab.app.transformer.UniversalTransformer;

public class GradeDao {
	UniversalTransformer<Grade> universalTransformer;

	public GradeDao() {
		universalTransformer = new UniversalTransformer<>();
	}

	public List<Grade> getAll() {
		Connection connection = ConnectionFactory.getConnection();
		List<Grade> gradeList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			gradeList = universalTransformer.transformAll(statement.executeQuery("SELECT * FROM `grade`"));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeList;
	}

	public void update(Grade grade) {
		Connection connection = ConnectionFactory.getConnection();
		String updateQuery = String
				.format("UPDATE `grade` SET `examDate` = ?, `grade` = ? WHERE `studentId` = ? AND `courseId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setDate(1, grade.getExamDate());
			preparedStatement.setInt(2, grade.getGrade());
			preparedStatement.setInt(3, grade.getStudentId());
			preparedStatement.setInt(4, grade.getCourseId());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(Grade grade) {
		Connection connection = ConnectionFactory.getConnection();
		String createQuery = String.format("INSERT INTO `grade` VALUES (?, ?, ?, ?)");
		try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
			preparedStatement.setInt(1, grade.getStudentId());
			preparedStatement.setInt(2, grade.getCourseId());
			preparedStatement.setDate(3, grade.getExamDate());
			preparedStatement.setInt(4, grade.getGrade());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(int studentId, int courseId) {
		Connection connection = ConnectionFactory.getConnection();
		String deleteQuery = String.format("DELETE FROM `grade` WHERE `studentId` = ? AND `courseId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Grade readById(int studentId, int courseId) {
		Connection connection = ConnectionFactory.getConnection();
		String deleteQuery = String.format("SELECT * FROM `grade` WHERE `studentId` = ? AND `courseId` = ?");
		Grade grade = new Grade();
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);
			grade = universalTransformer.transformAll(preparedStatement.executeQuery()).get(0);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grade;
	}
}
