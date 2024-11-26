package cafeteria.connectionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// connecção banco de dados

public class DatabaseConnection{
    private static final String URL = "http://localhost:8880/browser/";
    private static final String USER = "senai";
    private static final String PASSWORD = "senai";

    // connecção
      public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}