package com.epam.lab.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.dao.CourseDao;
import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Course;
import com.epam.lab.app.transformer.UniversalTransformer;

public class CourseService {
	private CourseDao courseDao;

	public CourseService() {
		courseDao = new CourseDao();
	}

	public List<Course> getAll() {
		return courseDao.getAll();
	}

	public void update(Course course) {
		courseDao.update(course);
	}

	public void create(Course course) {
		courseDao.create(course);
	}

	public void deleteById(int courseId) {
		courseDao.deleteById(courseId);
	}

	public Course readById(int courseId) {
		return courseDao.readById(courseId);
	}

	public List<Course> getAllCoursesBySpeciality(int specialityId) {
		Connection connection = ConnectionFactory.getConnection();
		List<Course> courseList = new ArrayList<>();
		String updateQuery = String.format("SELECT * FROM `course` WHERE `specialityId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setInt(1, specialityId);
			ResultSet resultSet = preparedStatement.executeQuery();
			UniversalTransformer<Course> universalTransformer = new UniversalTransformer<>();
			courseList = universalTransformer.transformAll(resultSet);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}
}
