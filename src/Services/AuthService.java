package Services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class AuthService berguna untuk fitur autentikasi dan untuk mengambil data
 * student dari database.
 *
 * @author natha
 */
public class AuthService {

    // SQL Query untuk autentikasi student menggunakan nim dan password (login).
    private static final String AUTH_QUERY = "SELECT 1 FROM student WHERE nim = ? AND password = ?";

    // SQL Query untuk mengambil data nama student berdasarkan NIM.
    private static final String NAME_QUERY = "SELECT name FROM student WHERE nim = ?";

    /**
     * Autentikasi student menggunakan nim dan password.
     *
     * @param nim nim merupakan nim dari student (unique/identifier).
     * @param password password akun student.
     * @return {@code true} bila nim dan password valid (Login Berhasil);
     * {@code false} bila tidak valid (Login Gagal);
     */
    public boolean authenticate(String nim, String password) {
        // Melakukan koneksi ke database.
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(AUTH_QUERY)) {

            statement.setString(1, nim); // Set NIM untuk query.
            statement.setString(2, password); // Set password untuk query.

            // Melakukan pengecekan di database apakah ada data di database yang cocok dengan NIM dan password yang dimasukkan.
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // return true apabila ditemukan data yang cocok, sebaliknya false.
            }

        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
            return false;
        }
    }

    /**
     * Mendapatkan data nama dari student dari NIM.
     *
     * @param nim nim merupakan NIM dari student (unique/identifier).
     * @return {@code String} Nama student bila NIM ditemukan; {@code null} Bila
     * tidak ditemukan.
     */
    public String getUserName(String nim) {
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(NAME_QUERY)) {

            statement.setString(1, nim); // Set NIM untuk query.

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name"); // Return nama student.
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user name: " + e.getMessage());
        }
        return null;
    }
}
