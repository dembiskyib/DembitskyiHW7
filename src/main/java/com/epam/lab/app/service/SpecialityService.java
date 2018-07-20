package com.epam.lab.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.lab.app.dao.SpecialityDao;
import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Speciality;

public class SpecialityService {
	private SpecialityDao specialityDao;

	public SpecialityService() {
		specialityDao = new SpecialityDao();
	}

	public List<Speciality> getAll() {
		return specialityDao.getAll();
	}

	public void update(Speciality speciality) {
		specialityDao.update(speciality);
	}

	public void create(Speciality speciality) {
		specialityDao.create(speciality);
	}

	public void deleteById(int specialityId) {
		specialityDao.deleteById(specialityId);
	}

	public Speciality readById(int specialityId) {
		return specialityDao.readById(specialityId);
	}

	@SuppressWarnings("resource")
	public void deleteWithTransfer(int specialityId) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String checkSpecialityQuery = String
				.format("SELECT `specialityId` FROM `speciality` WHERE `specialityId` <> ?");
		String updateQuery = String.format("UPDATE `student` SET `specialityId` = ? WHERE `specialityId` = ?");
		String deleteQuery = String.format("DELETE FROM `speciality` WHERE `specialityId` = ?");
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(checkSpecialityQuery);
			preparedStatement.setInt(1, specialityId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int newSpecialityId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(1, newSpecialityId);
			preparedStatement.setInt(2, specialityId);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, specialityId);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
