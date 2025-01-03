import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private static final String AUTH_QUERY = "SELECT 1 FROM student WHERE nim = ? AND password = ?";
    private static final String NAME_QUERY = "SELECT name FROM student WHERE nim = ?";

    public boolean authenticate(String nim, String password) {
        try (Connection connection = DatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(AUTH_QUERY)) {

            statement.setString(1, nim);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
            
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
            return false;
        }
    }
    
    public String getUserName(String nim) {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(NAME_QUERY)) {

            statement.setString(1, nim);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user name: " + e.getMessage());
        }
        return null;
    }
}
