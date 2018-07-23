package com.epam.lab.app.task4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;

public class MetaDataReader {
	private static final Logger logger = LogManager.getLogger(MetaDataReader.class);
	private Connection connection;
	private DatabaseMetaData databaseMetaData;

	public void printMetaData() {
		connection = ConnectionFactory.getConnection();
		try {
			databaseMetaData = connection.getMetaData();
			printTables(databaseMetaData.getTables(null, null, null, new String[] { "TABLE" }));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void printTables(ResultSet tableSet) throws SQLException {
		while (tableSet.next()) {
			String tableName = tableSet.getString("TABLE_NAME");
			logger.info("TABLE \"" + tableName + "\"");
			printTableAttributes(databaseMetaData.getColumns(null, null, tableName, null));
			printPrimaryKeys(databaseMetaData.getPrimaryKeys(null, null, tableName));
			printForeignKeys(databaseMetaData.getImportedKeys(null, null, tableName));
		}
		tableSet.close();
	}

	private void printTableAttributes(ResultSet columnSet) throws SQLException {
		logger.info("COLUMNS");
		logger.info(String.format("%30s %20s %20s %20s %20s", "COLUMN_NAME", "DATA_TYPE", "COLUMN_SIZE", "IS_NULLABLE",
				"IS_AUTOINCREMENT"));
		while (columnSet.next()) {
			String columnName = columnSet.getString("COLUMN_NAME");
			String datatype = columnSet.getString("DATA_TYPE");
			String columnsize = columnSet.getString("COLUMN_SIZE");
			String isNullable = columnSet.getString("IS_NULLABLE");
			String is_autoIncrment = columnSet.getString("IS_AUTOINCREMENT");
			logger.info(String.format("%30s %20s %20s %20s %20s", columnName, datatype, columnsize, isNullable,
					is_autoIncrment));
		}
		columnSet.close();
	}

	private void printPrimaryKeys(ResultSet primaryKeysSet) throws SQLException {
		logger.info("PRIMARY KEYS");
		while (primaryKeysSet.next()) {
			logger.info(primaryKeysSet.getString("COLUMN_NAME"));
		}
		primaryKeysSet.close();

	}

	private void printForeignKeys(ResultSet foreignKeysSet) throws SQLException {
		logger.info("FOREIGN KEYS");
		while (foreignKeysSet.next()) {
			logger.info(foreignKeysSet.getString("PKTABLE_NAME") + "." + foreignKeysSet.getString("PKCOLUMN_NAME") + "="
					+ foreignKeysSet.getString("FKTABLE_NAME") + "." + foreignKeysSet.getString("FKCOLUMN_NAME"));
		}
		foreignKeysSet.close();
	}

}
