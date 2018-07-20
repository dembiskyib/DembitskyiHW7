package com.epam.lab.app.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.model.Student;

public class StudentTransformer {
	public List<Student> transformAll(ResultSet resultSet) {
		List<Student> studentList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Student student = new Student(resultSet.getInt(1));
				student.setSecondName(resultSet.getString(2));
				student.setFirstName(resultSet.getString(3));
				student.setPatronymic(resultSet.getString(4));
				student.setMotherFullName(resultSet.getString(5));
				student.setFatherFullName(resultSet.getString(6));
				student.setAddress(resultSet.getString(7));
				student.setPhone(resultSet.getString(8));
				student.setPassportData(resultSet.getString(9));
				student.setRecordBookNumber(resultSet.getInt(10));
				student.setStartDate(resultSet.getDate(11));
				student.setGroup(resultSet.getString(12));
				student.setYear(resultSet.getInt(13));
				student.setSpecialityId(resultSet.getInt(14));
				student.setOnExtramural(resultSet.getBoolean(15));
				studentList.add(student);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
	}
}
