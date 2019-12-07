package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String dbUrl = "jdbc:postgres://ljnbtitm:wD1MAm...@isilo.db.elephantsql.com:5432/ljnbtitm";
    private static final String userName = "ljnbtitm";
    private static final String password = "wD1MAmn2Cndg1c6bYS3Nta0Jqxi9HnvK";

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
