package com.epam.lab.app.transformer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.epam.lab.app.annotations.Table;

public class UniversalTransformer<T> {
	public List<T> transformAll(ResultSet resultSet) {
		List<T> rowsList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				rowsList.add(transformRaw(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsList;
	}

	@SuppressWarnings("unchecked")
	private T transformRaw(ResultSet resultSet) throws SQLException {
		Class<?> rowClass = checkClass(resultSet);
		T row = null;
		try {
			row = (T) rowClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		List<Field> fieldsList = Arrays.asList(rowClass.getDeclaredFields());
		for (int i = 0; i < fieldsList.size(); i++) {
			Field field = fieldsList.get(i);
			if (!Objects.isNull(field.getAnnotation(com.epam.lab.app.annotations.Field.class))) {
				field.setAccessible(true);
				try {
					field.set(row, resultSet.getObject(checkAttributeName(field)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
		return row;

	}

	private Class<?> checkClass(ResultSet resultSet) {
		Class<?> rowClass = null;
		ResultSetMetaData resultSetMeta;
		try {
			resultSetMeta = resultSet.getMetaData();
			String className = resultSetMeta.getTableName(1);
			className = className.substring(0, 1).toUpperCase() + className.substring(1);
			Class<?> tempClass = Class.forName(String.format("com.epam.lab.app.model.%s", className));
			if (Objects.nonNull(tempClass.getAnnotation(Table.class))) {
				rowClass = tempClass;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rowClass;
	}

	private String checkAttributeName(Field field) {
		String name = field.getAnnotation(com.epam.lab.app.annotations.Field.class).value();
		if (name.equals("")) {
			name = field.getName();
		}
		return name;
	}
}
