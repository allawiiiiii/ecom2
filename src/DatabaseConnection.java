import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:webbutiken.db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed");
        }
    }
}

