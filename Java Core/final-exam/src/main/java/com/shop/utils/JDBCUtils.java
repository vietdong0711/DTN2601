package com.shop.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {


    public static Connection getConnection() throws IOException {
        String url = "jdbc:mysql://localhost:3306/final_exam";
        String username = "root";
        String password = "root";// mk mysql
//        prop.load(new FileInputStream("src/main/resources/database.properties"));
//        String url = prop.getProperty("url");
//        String username = prop.getProperty("username");
//        String password = prop.getProperty("password");
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected to database successfully");
        }  catch (Exception ex) {
            System.out.println("Kết nối DB ko thành công");
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet rs) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
