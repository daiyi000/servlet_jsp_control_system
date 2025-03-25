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
            System.out.println("[������Ϣ] MySQL JDBC �������سɹ���");
        } catch (ClassNotFoundException e) {
            System.err.println("[����] MySQL JDBC ��������ʧ�ܣ�");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[������Ϣ] ���ݿ����ӳɹ���");
            return connection;
        } catch (SQLException e) {
            System.err.println("[����] ���ݿ�����ʧ�ܣ�");
            System.err.println("����URL: " + URL);
            System.err.println("�û���: " + USER);
            System.err.println("������Ϣ: " + e.getMessage());
            e.printStackTrace();
            return null;
            
        }
        
    }
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("[������Ϣ] ���ݿ������ѹرգ�");
            } catch (SQLException e) {
                System.err.println("[����] ���ݿ����ӹر�ʧ�ܣ�");
                e.printStackTrace();
            }
        }
    }
}
