package com.example.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Simple {


    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/database_name";
        String password = "djooky";
        String userName = "djooky";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("not connection");
        }
        return connection;
    }
}
