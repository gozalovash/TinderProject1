package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String dbUrl ="jdbc:postgresql://isilo.db.elephantsql.com:5432/ljnbtitm";
    private static final String userName ="ljnbtitm";
    private static final String password ="wD1MAmn2Cndg1c6bYS3Nta0Jqxi9HnvK";

    public static Connection connection;

    private static Connection connect() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(dbUrl, userName, password);
    }


    public static Connection connection() {
        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException | ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        return connection;
    }


}
