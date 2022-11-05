package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 Represents the Authentication service in the application
 @author Samuel Ong
 @version 1.0
 @since 2022-11-03
 */
public class StaffAuthenticator implements UserAuthenticator {
    /**
     * Map of Username and Password are stored here
     */
    private final Map<String, String> userAccounts;
    /**
     * Boolean to check if there's an account logged in
     */
    private boolean loggedIn = false;

    /**
     * The constructor to retrieve all usernames and passwords
     */
    public StaffAuthenticator() {
        userAccounts = new HashMap<>();
        try {
            Scanner scStream = new Scanner(new File("./cinemadata/staffacc.txt" ));
            String inputLine;
            while (scStream.hasNext())
            {
                inputLine = scStream.nextLine();
                String[] temp = inputLine.split(";");
                userAccounts.put(temp[0], temp[1]);
            }
            scStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Staff account file unable to be found!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO error!" + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * To log in as a user
     * @param username Username for identification
     * @param password Password for verification
     * @return whether logged was a success
     */
    @Override
    public boolean login(String username, String password) {
        if (validateUser(username, password)) {
            loggedIn = true;
            return true;
        }
        loggedIn = false;
        return false;
    }

    /**
     * To log out
     */
    @Override
    public void logout() {
        loggedIn = false;
    }

    /**
     * To validate the Username and Password
     * @param username Username
     * @param password Password
     * @return Whether the user is valid
     */
    private boolean validateUser(String username, String password) {
        String user = userAccounts.get(username);
        if (user != null) {
            return user.equals(password);
        }
        return false;
    }
}
