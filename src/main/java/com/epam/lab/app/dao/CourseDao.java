package com.epam.lab.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Course;
import com.epam.lab.app.transformer.CourseTransformer;

public class CourseDao {
	CourseTransformer courseTransformer;

	public CourseDao() {
		courseTransformer = new CourseTransformer();
	}

	public List<Course> getAll() {
		Connection connection = ConnectionFactory.getConnection();
		List<Course> courseList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			courseList = courseTransformer.transformAll(statement.executeQuery("SELECT * FROM `course`"));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

	public void update(Course course) {
		Connection connection = ConnectionFactory.getConnection();
		String updateQuery = String.format(
				"UPDATE `course` SET `courseName` = ?, `courseDescription` = ?, `specialityId` = ? WHERE `courseId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, course.getCourseName());
			preparedStatement.setString(2, course.getCourseDescription());
			preparedStatement.setInt(3, course.getSpecialityId());
			preparedStatement.setInt(4, course.getCourseId());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(Course course) {
		Connection connection = ConnectionFactory.getConnection();
		String createQuery = String.format("INSERT INTO `course` VALUES (?, ?, ?, ?)");
		try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
			preparedStatement.setInt(1, course.getCourseId());
			preparedStatement.setString(2, course.getCourseName());
			preparedStatement.setString(3, course.getCourseDescription());
			preparedStatement.setInt(4, course.getSpecialityId());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(int courseId) {
		Connection connection = ConnectionFactory.getConnection();
		String deleteQuery = String.format("DELETE FROM `course` WHERE `courseId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Course readById(int courseId) {
		Connection connection = ConnectionFactory.getConnection();
		String createQuery = String.format("SELECT * FROM `course` WHERE `courseId` = ?");
		Course course = new Course();
		try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
			preparedStatement.setInt(1, courseId);
			course = courseTransformer.transformAll(preparedStatement.executeQuery()).get(0);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
}
