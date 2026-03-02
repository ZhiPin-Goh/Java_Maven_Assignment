package Models;

public class SessionManager {
    private static int currentUserID = 0;
    private static String currentUserEmail = "";
    public static void setUserId(int id) {
        currentUserID = id;
    }

    public static int getUserId() {
        return currentUserID;
    }

    public static boolean isLoggedIn() {
        return currentUserID != 0;
    }

    public static void clearSession() {
        currentUserID = 0;
        currentUserEmail = "";
    }

    public static void setUserEmail(String email) { currentUserEmail = email; }
    public static String getUserEmail() { return currentUserEmail; }
}
