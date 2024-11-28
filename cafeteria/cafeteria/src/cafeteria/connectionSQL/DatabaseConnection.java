package cafeteria.connectionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// connecção banco de dados

public class DatabaseConnection{
    private static final String URL = "jdbc:postgresql://localhost:5438/cafeteria";
    private static final String USER = "senai";
    private static final String PASSWORD = "senai";

    // connecção
      public static Connection getConnection()  {
        try {
          return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return null;
    }
}