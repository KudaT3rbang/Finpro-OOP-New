package Models;

public class AdminSession {

    private static AdminSession instance;

    private int adminId;
    private String username;

    // Private constructor to enforce singleton pattern
    private AdminSession() {}

    /**
     * Get the singleton instance of AdminSession.
     *
     * @return The AdminSession instance.
     */
    public static AdminSession getInstance() {
        if (instance == null) {
            instance = new AdminSession();
        }
        return instance;
    }

    /**
     * Set the admin's session details.
     *
     * @param adminId  The admin's ID.
     * @param username The admin's username.
     */
    public void setAdminDetails(int adminId, String username) {
        this.adminId = adminId;
        this.username = username;
    }

    /**
     * Clear the admin session.
     */
    public void clearSession() {
        adminId = 0;
        username = null;
    }

    /**
     * Get the admin's ID.
     *
     * @return The admin's ID.
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * Get the admin's username.
     *
     * @return The admin's username.
     */
    public String getUsername() {
        return username;
    }
}
