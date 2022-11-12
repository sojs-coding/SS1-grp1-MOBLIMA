package boundary.movieGoerUI;

import controller.MovieGoerAuthenticator;
import entity.booking.MovieGoer;
import controller.Initialization;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File; 
import java.io.FileNotFoundException;

public class MovieGoerUI {
    private Initialization initObj = Initialization.getINSTANCE();
    /* public void showAvailableSeats(){
        int rowLength = 10;
        int row =0;
        for (int i = 0; i < rowLength / 2; i++) {
            System.out.print("===");
        }
        System.out.print("  S C R E E N  ");
        for (int i = rowLength / 2; i < rowLength - 1; i++) {
            System.out.print("===");
        }
            System.out.print("\n\n");
        for (int j =0; j<rowLength; j++) {
            System.out.print(row + "  ");
            for (int i = 0; i < rowLength / 2; i++) {
                System.out.print("0");
            }
            for (int i = 0; i < 3; i++) {
                System.out.print("   ");
            }
            for (int i = rowLength / 2; i < rowLength; i++) {
                System.out.print("0");
            }
            System.out.println();
            row++;
        }
        System.out.print("\n\n");

        for (int i = 0; i < rowLength / 2 - 1; i++) {
            System.out.print("   ");
        }
        System.out.print(" | E N T R A N C E |");

        System.out.print("\n\n\n\n");
    } */
    
    
    public void registerMovieGoer(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Mobile : ");
        String newMobile = sc.nextLine();
        System.out.print("Name : ");
        String newName = sc.nextLine();
        System.out.print("Password : ");
        String newPass = sc.nextLine();
        System.out.print("Email : ");
        String newEmail = sc.nextLine();
        try (FileWriter f = new FileWriter( "./cinemadata/moviegoeracc.txt" , true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {

            p.println(newMobile+";"+newName+";"+newPass+";"+newEmail);

        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("Successfully Registered! Thank you!");
        MovieGoerUI movieGoerUI = new MovieGoerUI();
        System.out.println("Please Login");
        movieGoerUI.login();
    }
    

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mobile : ");
        String mobile = sc.nextLine();
        System.out.print("Password : ");
        String pass = sc.nextLine();
        MovieGoerAuthenticator movieGoerAuthenticator = initObj.getMovieGoerAuthenticator();
        movieGoerAuthenticator.login(mobile, pass);
        if (MovieGoerAuthenticator.user != null) {
            movieGoerMenu(MovieGoerAuthenticator.user);
        }
        else{
            System.out.print("Incorrect mobile/password ");
            System.out.print("Please Re-enter");
            MovieGoerUI movieGoerUI = new MovieGoerUI();
            movieGoerUI.login()
            }
    
        /*String email;
        String email2;
        int choices;
        Boolean isStillrunning = true;
        System.out.println("=====================================");
        System.out.println("Welcome to Movie-Goer Page");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter your Email: ");
            email = scanner.nextLine();
            System.out.println("Please Re-enter your Email: ");
            email2 = scanner.nextLine();
            if (email.equals(email2) == true) {
                movieGoerMenu();
                break;
            } else {
                System.out.println("Try again! Email do not match! ");
            }
        }
        //code to check whether email exist in database*/
    }

    public void movieGoerMenu(MovieGoer user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================================");
        System.out.println("Welcome to Movie-Goer Page");
        boolean isStillrunning = true;
        BookingUI bookingUI = new BookingUI(user,initObj.getMovieManager(),initObj.getCineplexManager(),initObj.getShowtimeManager(),initObj.getBookingManager(),initObj.getTicketPriceManager());
        MovieUI movieUI = new MovieUI(initObj.getMovieManager());

        while (isStillrunning) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("                           Booking Menu                             ");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) List movies available: ");
            System.out.println("(2) Search movies available: ");
            System.out.println("(3) View Movie Details: ");
            System.out.println("(4) Book and purchase ticket: ");
            System.out.println("(5) Show booking history");
            System.out.println("(6) View Top 5 movies ranked by ticket sales: ");
            System.out.println("(7) Leave Rating");
            System.out.println("(8) Quit");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Enter your choice:");
            String choices = scanner.nextLine();
            switch (choices) {
                case "1":
                    System.out.println("(1) List movies available");
                    //get
                    //function to show get availble movies
                    movieUI.printAllMovies();
                    break;
                case "2":
                    System.out.println("(2) Search movies available");
                    //searchMovie();
                    //search function
                    movieUI.searchMovie();
                    break;
                case "3":
                    System.out.println("(3) View Movie Details");
                    //get movie details
                    movieUI.viewMovieDetails();
                    break;
                case "4":
                    System.out.println("(4) Book and purchase ticket");
                    bookingUI.BookAndPurchase();
                    //function to book and purchase
                    break;
                case "5":
                    System.out.println("(5) Show booking history");
                    bookingUI.BookingHistory();
                    // function to get booing details
                    break;
                case "6":
                    System.out.println("(6) View Top 5 movies ranked by ticket sales ");
                    bookingUI.TopFiveMovie();
                    //function to get top 5
                    break;
                case "7":
                    System.out.println("(7) Leave Rating");
                    //leave ratings
                    movieUI.leaveReview();
                    break;
                case "8":
                    isStillrunning = false;
                    System.out.println("bye");
                    break;
            }
        }
    }
}
