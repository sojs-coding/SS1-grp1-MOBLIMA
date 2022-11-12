package boundary;

import boundary.movieGoerUI.MovieGoerUI;
import boundary.staffUI.StaffLoginUI;

import java.util.Scanner;

public class StartUpUI {
    public void start() {
        String choice;
        Boolean isRunning = true;
        while (isRunning) {
            System.out.println("=====================================");
            System.out.println("Welcome to MOLIMA");
            System.out.println("Are you an Admin or Movie-goer? ");
            System.out.println("1. Admin Module");
            System.out.println("2. Movie-goer Module");
            System.out.println("3. Exit Application");
            System.out.println("=====================================");


            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    StaffLoginUI staffLoginUI = new StaffLoginUI();
                    staffLoginUI.login();
                    break;
                case "2":
                    MovieGoerUI movieGoerUI = new MovieGoerUI();
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            movieGoerUI.login();
                            break;
                        case "2":
                            movieGoerUI.registerMovieGoer();
                            break;
                    }
                case "3":
                    isRunning = false;
                    System.out.println("bye");
                    break;
            }
        }
    }
}
