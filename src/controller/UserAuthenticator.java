package controller;

public interface UserAuthenticator {
    boolean login(String identifier, String password);
    void logout();
}
