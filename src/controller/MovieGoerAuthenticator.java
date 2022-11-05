package controller;

import entity.MovieGoer;
import entity.movie.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieGoerAuthenticator implements UserAuthenticator {
    /**
     * Map of Username and Password are stored here
     */
    private final Map<String, Map<MovieGoerData, String>> userAccounts;

    /**
     * User
     */
    public static MovieGoer user;

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
        } catch (IOException e) {
            System.out.println("IO error!" + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public boolean login(String mobile, String password) {
        if (validateUser(mobile, password)) {
            Map<MovieGoerData, String> data = userAccounts.get(mobile);
            user = new MovieGoer(data.get(MovieGoerData.NAME), Integer.parseInt(mobile), data.get(MovieGoerData.EMAIL));
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        user = null;
    }

    private boolean validateUser(String mobile, String password) {
        Map<MovieGoerData, String> data = userAccounts.get(mobile);
        if (data != null) {
            String pass = data.get(MovieGoerData.PASSWORD);
            return pass.equals(password);
        }
        return false;
    }
}

enum MovieGoerData {
    NAME,
    PASSWORD,
    EMAIL,
    MOBILE
}