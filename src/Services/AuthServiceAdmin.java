package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Kelas AuthServiceAdmin menyediakan metode untuk melakukan autentikasi dan
 * pengelolaan data admin. Berfungsi untuk menghubungkan aplikasi dengan tabel
 * `admin` di database.
 */
public class AuthServiceAdmin {

    private static final String AUTH_QUERY = "SELECT 1 FROM admin WHERE username = ? AND password = ?";
    private static final String NAME_QUERY = "SELECT username FROM admin WHERE username = ?";

    /**
     * Melakukan autentikasi admin berdasarkan username dan password.
     *
     * @param username Username admin yang akan diverifikasi.
     * @param password Password admin yang akan diverifikasi.
     * @return `true` jika autentikasi berhasil, `false` jika gagal.
     */
    public boolean authenticateAdmin(String username, String password) {
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(AUTH_QUERY)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            System.err.println("Error during admin authentication: " + e.getMessage());
            return false;
        }
    }

    /**
     * Mengambil ID admin berdasarkan username dari database.
     *
     * @param username Username admin yang datanya akan diambil.
     * @return ID admin jika ditemukan, 0 jika tidak ditemukan.
     */
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
        return 0;
    }

}
