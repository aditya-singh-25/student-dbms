package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Mysql@2502";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ DB connection established!");
        } catch (Exception e) {
            System.out.println("❌ DB connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
