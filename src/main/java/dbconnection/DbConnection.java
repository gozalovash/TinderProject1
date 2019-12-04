package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/......";
    private static final String userName = "postgres";
    private static final String password = "......";

    private static Connection connection;

    private DbConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {

                connection = DriverManager.getConnection(dbUrl, userName, password);

            } catch (SQLException e) {
                throw new RuntimeException("Something went wrong during connection");

            }
        }
        return connection;
    }

}
