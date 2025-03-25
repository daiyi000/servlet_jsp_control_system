package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/iphone?useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("[调试信息] MySQL JDBC 驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.err.println("[错误] MySQL JDBC 驱动加载失败！");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[调试信息] 数据库连接成功！");
            return connection;
        } catch (SQLException e) {
            System.err.println("[错误] 数据库连接失败！");
            System.err.println("连接URL: " + URL);
            System.err.println("用户名: " + USER);
            System.err.println("错误信息: " + e.getMessage());
            e.printStackTrace();
            return null;
            
        }
        
    }
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("[调试信息] 数据库连接已关闭！");
            } catch (SQLException e) {
                System.err.println("[错误] 数据库连接关闭失败！");
                e.printStackTrace();
            }
        }
    }
}
