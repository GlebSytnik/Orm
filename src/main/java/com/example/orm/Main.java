package com.example.orm;

import org.postgresql.Driver;

import java.sql.*;

public class Main {
    private static final String url ="jdbc:postgresql://localhost:5432/database_name";
    private static final String password = "djooky";
    private static final String userName = "djooky";
    public  static Driver driver = new Driver();


    public static void main(String[] args)  {
        try( Connection connection = DriverManager.getConnection(url,userName,password);
             Statement statement = connection.createStatement()) {
            DriverManager.registerDriver(driver);
            statement.execute("INSERT INTO orm.data (id,first_name,last_name,mail) VALUES (2,'Роман','Шевченко','roma@mail.ru')");

            if(!connection.isClosed()){
               System.out.println("ohuenno");
            }
        }
        catch( SQLException e ){
            System.out.println("Don't exit");
        }
    }
}
