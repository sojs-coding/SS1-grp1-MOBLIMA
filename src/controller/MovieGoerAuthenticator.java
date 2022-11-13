package controller;

import entity.booking.MovieGoer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 Represents the Authentication service in the application
 @author Samuel Ong
 @version 1.0
 @since 2022-11-03
 */
public class MovieGoerAuthenticator implements UserAuthenticator {
    /**
     * Represents the Map of Username and Password are stored here
     */
    private final Map<String, Map<MovieGoerData, String>> userAccounts;

    /**
     * The MovieGoer Object currently logged in
     */
    public static MovieGoer user;

    /**
     * Constructor for the MovieGoer Authenticator
     * Reads the textfile for the MovieGoer Accounts
     */
    public MovieGoerAuthenticator() {
        userAccounts = new HashMap<>();
        try {
            Scanner scStream = new Scanner(new File("./cinemadata/moviegoeracc.txt" ));
            String inputLine;
            while (scStream.hasNext())
            {
                inputLine = scStream.nextLine();
                String[] temp = inputLine.split(";");
                Map<MovieGoerData, String> value = new HashMap<>();
                value.put(MovieGoerData.NAME, temp[1]);
                value.put(MovieGoerData.PASSWORD, temp[2]);
                value.put(MovieGoerData.EMAIL, temp[3]);
                userAccounts.put(temp[0], value);
            }
            scStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Staff account file unable to be found!");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("IO error!" + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * The Method to login. If the MovieGoer is found, it is passed into the user variable
     * @param mobile Mobile Number to compare against
     * @param password The password to validate the user
     * @return Whether the login was a success
     */
    @Override
    public boolean login(String mobile, String password) {
        if (validateUser(mobile, password)) {
            Map<MovieGoerData, String> data = userAccounts.get(mobile);
            user = new MovieGoer(data.get(MovieGoerData.NAME), mobile.toCharArray(), data.get(MovieGoerData.EMAIL));
            return true;
        }
        return false;
    }

    /**
     * Removes the current User
     */
    @Override
    public void logout() {
        user = null;
    }

    /**
     * To check if the mobile and password is correct
     * @param mobile Mobile Number of the User
     * @param password Password for the Account
     * @return The success of the validation
     */
    private boolean validateUser(String mobile, String password) {
        Map<MovieGoerData, String> data = userAccounts.get(mobile);
        if (data != null) {
            String pass = data.get(MovieGoerData.PASSWORD);
            return pass.equals(password);
        }
        return false;
    }
}
/**
 The Enumerator represents the MovieGoerData Format when retrieved from the File
 @author Samuel Ong
 @version 1.0
 @since 2022-11-03
 */
enum MovieGoerData {
    NAME,
    PASSWORD,
    EMAIL,
    MOBILE
}