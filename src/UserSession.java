public class UserSession {

    private static UserSession instance;

    private String nim;
    private String name;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUserDetails(String nim, String name) {
        this.nim = nim;
        this.name = name;
    }

    public void clearSession() {
        nim = null;
        name = null;
    }

    public String getNim() {
        return nim;
    }

    public String getName() {
        return name;
    }
}
