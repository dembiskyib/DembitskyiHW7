package com.epam.lab.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.model.Speciality;
import com.epam.lab.app.transformer.SpecialityTransformer;

public class SpecialityDao {
	SpecialityTransformer specialityTransformer;

	public SpecialityDao() {
		specialityTransformer = new SpecialityTransformer();
	}

	public List<Speciality> getAll() {
		Connection connection = ConnectionFactory.getConnection();
		List<Speciality> specialityList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			specialityList = specialityTransformer.transformAll(statement.executeQuery("SELECT * FROM `speciality`"));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specialityList;
	}

	public void update(Speciality speciality) {
		Connection connection = ConnectionFactory.getConnection();
		String updateQuery = String.format(
				"UPDATE `speciality` SET `specialityName` = ?, `specialityDescription` = ? WHERE `specialityId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, speciality.getSpecialityName());
			preparedStatement.setString(2, speciality.getSpecialityDescription());
			preparedStatement.setInt(3, speciality.getSpecialityId());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(Speciality speciality) {
		Connection connection = ConnectionFactory.getConnection();
		String createQuery = String.format("INSERT INTO `speciality` VALUES (?, ?, ?)");
		try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
			preparedStatement.setInt(1, speciality.getSpecialityId());
			preparedStatement.setString(2, speciality.getSpecialityName());
			preparedStatement.setString(3, speciality.getSpecialityDescription());
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(int specialityId) {
		Connection connection = ConnectionFactory.getConnection();
		String deleteQuery = String.format("DELETE FROM `speciality` WHERE `specialityId` = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, specialityId);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Speciality readById(int specialityId) {
		Connection connection = ConnectionFactory.getConnection();
		String deleteQuery = String.format("SELECT * FROM `speciality` WHERE `specialityId` = ?");
		Speciality speciality = new Speciality();
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, specialityId);
			preparedStatement.executeQuery();
			speciality = specialityTransformer.transformAll(preparedStatement.executeQuery()).get(0);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return speciality;
	}

}
