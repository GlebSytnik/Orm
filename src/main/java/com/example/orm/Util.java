package com.example.orm;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Util {

    public static String[] getColumnNames(ResultSetMetaData metadata) throws SQLException {
        int columnCount = metadata.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metadata.getColumnLabel(i + 1);
        }
        return columnNames;
    }

    public int getColumnCount(ResultSetMetaData metadata) throws SQLException {
        return metadata.getColumnCount();
    }


}
