package com.epam.lab.app.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.model.Grade;

public class GradeTransformer {
	public List<Grade> transformAll(ResultSet resultSet) {
		List<Grade> gradeList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				gradeList.add(
						new Grade(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getInt(4)));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeList;
	}
}
