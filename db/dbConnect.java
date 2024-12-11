package doan1.QuanLyThuVien_DoAn1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QuanLyThuVien2; encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";

    public static Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                return connection;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}