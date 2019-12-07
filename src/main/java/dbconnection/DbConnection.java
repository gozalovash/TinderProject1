package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String dbUrl ="jdbc:postgres://ljnbtitm:wD1MAmn2Cndg1c6bYS3Nta0Jqxi9HnvK@isilo.db.elephantsql.com:5432/ljnbtitm";
    private static final String userName ="ljnbtitm";
    private static final String password ="wD1MAmn2Cndg1c6bYS3Nta0Jqxi9HnvK";

    public static Connection connection =null;

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(dbUrl, userName, password);
    }


    public Connection connection() {
        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new IllegalStateException();
            }
        }

        return this.connection;
    }


}
