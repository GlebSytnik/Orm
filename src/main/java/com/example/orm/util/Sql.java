package com.example.orm.util;

import com.example.orm.Entity;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Sql {

    private static String getCreateDatabaseSql(String databaseName) {
        return "CREATE DATABASE " + databaseName;
    }

    public static boolean executeSQL(Connection connection, String sql) {
        try {
            PreparedStatement preparedStatement = connection.preparedStatement(sql);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    public static String[] getColumnNames(ResultSetMetaData metadata) throws SQLException {
        int columnCount = metadata.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metadata.getColumnLabel(i + 1);
        }
        return columnNames;
    }

    public int getColumnCount(ResultSetMetaData metadata) {
        return metadata.getColumnCount();
    }

    public static String createTable(Class<? extends Object> classObject, Connection connection) throws SQLException {
        Field[] declaredFields = classObject.getDeclaredFields();
        Map<String, Object> nameAndTypeTable = new HashMap<>();
        for (Field field : declaredFields) {
            nameAndTypeTable.put(field.getName(), field.getType());
        }
        String carClassName = classObject.getName();
        String[] words = carClassName.split("\\.");
        String className = words[words.length - 1];
        String createTable = " create table " + className + "(" + nameAndTypeTable.get(0) + " " + nameAndTypeTable.get(0) + ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            System.out.println(SQLException.class);

        }

    }

}
}
