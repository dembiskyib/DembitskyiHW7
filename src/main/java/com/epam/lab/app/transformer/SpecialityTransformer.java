package com.epam.lab.app.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.model.Speciality;

public class SpecialityTransformer {
	public List<Speciality> transformAll(ResultSet resultSet) {
		List<Speciality> specialityList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				specialityList.add(new Speciality(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specialityList;
	}
}
