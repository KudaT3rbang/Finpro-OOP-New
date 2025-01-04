
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthServiceAdmin {

    private static final String AUTH_QUERY = "SELECT 1 FROM admin WHERE username = ? AND password = ?";
    private static final String NAME_QUERY = "SELECT username FROM admin WHERE username = ?";

    /**
     * Authenticate an admin using their username and password.
     *
     * @param username The admin's username.
     * @param password The admin's password.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticateAdmin(String username, String password) {
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(AUTH_QUERY)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a result exists, authentication is successful
            }

        } catch (SQLException e) {
            System.err.println("Error during admin authentication: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieve the admin's username from the database.
     *
     * @param username The admin's username.
     * @return The admin's username if found, null otherwise.
     */
    public String getAdminName(String username) {
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(NAME_QUERY)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching admin username: " + e.getMessage());
        }
        return null;
    }

    public int getAdminId(String username) {
        String query = "SELECT id FROM admin WHERE username = ?";
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching admin ID: " + e.getMessage());
        }
        return 0; // Return 0 if no admin ID is found
    }

}
