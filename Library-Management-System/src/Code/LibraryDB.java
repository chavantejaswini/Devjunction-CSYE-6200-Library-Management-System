package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryDB {
	
	private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String URL = "jdbc:mysql://localhost:3306/librarysql";
    private static final String USER = "root";
    private static final String PASSWORD = "abcd*123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // First try connecting to the database
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                // If database doesn't exist, create it
                if (e.getMessage().contains("Unknown database")) {
                    Connection adminConn = DriverManager.getConnection(BASE_URL, USER, PASSWORD);
                    Statement stmt = adminConn.createStatement();
                    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS librarysql");
                    stmt.close();
                    adminConn.close();
                    
                    // Now connect to the new database
                    return DriverManager.getConnection(URL, USER, PASSWORD);
                }
                throw e;
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
    }
}
