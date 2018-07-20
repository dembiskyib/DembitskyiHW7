package com.epam.lab.app.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.model.Course;

public class CourseTransformer {
	public List<Course> transformAll(ResultSet resultSet) {
		List<Course> courseList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				courseList.add(new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4)));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}
}
