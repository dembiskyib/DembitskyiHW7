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
	Connection connection;
	DatabaseMetaData databaseMetaData;

	public MetaDataReader() {
		connection = ConnectionFactory.getConnection();
	}

	public void printMetaData() {
		try {
			databaseMetaData = connection.getMetaData();
			printTables(databaseMetaData.getTables(null, null, null, new String[] { "TABLE" }));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printTables(ResultSet tableSet) {
		try {
			while (tableSet.next()) {
				String tableName = tableSet.getString("TABLE_NAME");
				logger.info("TABLE \"" + tableName + "\"");
				printTableAttributes(databaseMetaData.getColumns(null, null, tableName, null));
				printPrimaryKeys(databaseMetaData.getPrimaryKeys(null, null, tableName));
				printForeignKeys(databaseMetaData.getImportedKeys(null, null, tableName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printTableAttributes(ResultSet columnSet) {
		try {
			logger.info("COLUMNS");
			logger.info(String.format("%30s %20s %20s %20s %20s", "COLUMN_NAME", "DATA_TYPE", "COLUMN_SIZE",
					"IS_NULLABLE", "IS_AUTOINCREMENT"));
			while (columnSet.next()) {
				String columnName = columnSet.getString("COLUMN_NAME");
				String datatype = columnSet.getString("DATA_TYPE");
				String columnsize = columnSet.getString("COLUMN_SIZE");
				String isNullable = columnSet.getString("IS_NULLABLE");
				String is_autoIncrment = columnSet.getString("IS_AUTOINCREMENT");
				logger.info(String.format("%30s %20s %20s %20s %20s", columnName, datatype, columnsize, isNullable,
						is_autoIncrment));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printPrimaryKeys(ResultSet primaryKeysSet) {
		logger.info("PRIMARY KEYS");
		try {
			while (primaryKeysSet.next()) {
				logger.info(primaryKeysSet.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printForeignKeys(ResultSet foreignKeysSet) {
		logger.info("FOREIGN KEYS");
		try {
			while (foreignKeysSet.next()) {
				logger.info(foreignKeysSet.getString("PKTABLE_NAME") + "." + foreignKeysSet.getString("PKCOLUMN_NAME")
						+ "=" + foreignKeysSet.getString("FKTABLE_NAME") + "."
						+ foreignKeysSet.getString("FKCOLUMN_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
