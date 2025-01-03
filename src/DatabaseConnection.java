import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/university_management";
    private static final String USER = "root";
    private static final String PASSWORD = "338822";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.format("SQL State : %s\n%s", e.getSQLState(), e.getMessage());
        }
        return connection;
    }
}
