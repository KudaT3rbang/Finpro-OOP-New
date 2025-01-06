package Models;


/**
 * Class ini berguna untuk menyimpan data sesi student yang sedang login.
 *
 * @author natha
 */
public class UserSession {

    /**
     * Instance dari userSession.
     */
    private static UserSession instance;

    /**
     * NIM dari student yang login.
     */
    private String nim;

    /**
     * Nama dari student yang login.
     */
    private String name;

    /**
     * Konstruktor agar class dapat digunakan diluar class.
     */
    private UserSession() {
    }

    /**
     * Mendapatkan instance dari UserSession. Bila belum ada instance buat baru.
     *
     * @return instance UserSession.
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Menyimpan data student yang sudah login ke instance userSession.
     *
     * @param nim NIM dari student yang login.
     * @param name Nama dari student yang login.
     */
    public void setUserDetails(String nim, String name) {
        this.nim = nim;
        this.name = name;
    }

    /**
     * Menghapus data sesi student (Logout).
     */
    public void clearSession() {
        nim = null;
        name = null;
    }

    /**
     * Mendapatkan nim dari sesi.
     *
     * @return NIM student yang sedang login.
     */
    public String getNim() {
        return nim;
    }

    /**
     * Mendapatkan nama dari sesi.
     *
     * @return Nama student yang sedang login.
     */
    public String getName() {
        return name;
    }
}
