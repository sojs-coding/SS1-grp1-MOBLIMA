package controller;

public interface UserAuthenticator {
    boolean isLoggedIn();
    boolean login(String username, String password);
    void logout();
}
