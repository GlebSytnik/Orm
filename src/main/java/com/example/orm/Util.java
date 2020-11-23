package com.example.orm;

import java.sql.SQLException;

public class Util {

    public String[] getColumnNames() throws SQLException {
        int colN = metaData.getColumnCount();
        String[] columnNames = new String[colN];
        for (int colC = 0; colC < colN; colC++) {
            columnNames[colC] = metaData.getColumnLabel(colC + 1);
        }
        return columnNames;
    }
    public int getColumnCount() throws SQLException {
        return metaData.getColumnCount();
    }
}
