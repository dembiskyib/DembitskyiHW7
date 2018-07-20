package com.epam.lab.app.dataBaseInteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static int portNumber;
	public static String login;
	public static String password;
	public static boolean firstConnection = true;

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url;
			if (firstConnection) {
				url = String.format("jdbc:mysql://localhost:%d", portNumber);
				firstConnection = false;
			} else {
				url = String.format("jdbc:mysql://localhost:%d/university", portNumber);
			}
			connection = DriverManager.getConnection(url, login, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
