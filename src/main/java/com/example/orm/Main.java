package com.example.orm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.Driver;

import java.sql.*;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/database_name";
    private static final String password = "djooky";
    private static final String userName = "djooky";
    public static Driver driver = new Driver();
  //  public static ResultSetMetaData metaData;



    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement()) {
            if (!connection.isClosed()) {
                System.out.println("good");
            }

            DriverManager.registerDriver(driver);
           // ResultSet resultSet = statement.executeQuery("select * from orm.data");
           // metaData = resultSet.getMetaData();
           // String[] columnName = Util.getColumnNames(metaData);
           // statement.executeUpdate("create table entity (id varchar )");

            Entity entity = new Entity();
            Class<? extends Entity> carClass = entity.getClass();

            Field[] declaredFields = carClass.getDeclaredFields();

            List<String> columnNameArray = new ArrayList<>();
            List<Object> columnType = new ArrayList<>();
            for (Field field : declaredFields) {
                columnNameArray.add(field.getName());
                columnType.add(field.getType());
            }
            String carClassName = carClass.getName();
            String[] words = carClassName.split("\\.");
            String className = words[words.length-1];
            String createTable = " create table " + className + "(" + columnNameArray.get(0) + " " + columnType.get(0) + ")";
            statement.executeUpdate(createTable);


        } catch (SQLException e) {
            System.out.println("Don't exit");
            //бросать ексепшен
            //внедрить транзакции
            //сделать в мапе
        }


    }

}
