package cafeteria.vendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// connecção banco de dados

public class DatabaseConnection{
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    // connecção
      public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}