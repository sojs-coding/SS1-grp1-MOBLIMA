import java.util.Scanner;
import controller;

public class UI {
    static void showAvailableSeats(){
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
    }
    

    static void MovieGoerLogin(){
        String email;
        String email2;
        int choices;
        Boolean isStillrunning = true;
        System.out.println("=====================================");
        System.out.println("Welcome to Movie-Goer Page");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Please enter your Email: ");
            email= scanner.nextLine();
            System.out.println("Please Re-enter your Email: ");
            email2= scanner.nextLine();
            if (email.equals(email2)==true){
                break;
            }
            else{
                System.out.println("Try again! Email do not match! ");
            }
        }

        //code to check whether email exist in database
        System.out.println("=====================================");
        System.out.println("Welcome to Movie-Goer Page");
        while(isStillrunning){
            System.out.println("--------------------------------------------------------------------");
            System.out.println("                           Booking Menu                             ");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) List movies available: ");    
            System.out.println("(2) Search movies available: ");  
            System.out.println("(3) View Movie Details: ");      
            System.out.println("(4) Check seat availability: ");    
            System.out.println("(5) Book and purchase ticket: ");   
            System.out.println("(6) Show booking history"); 
            System.out.println("(7) View Top 5 movies ranked by ticket sales: ");   
            System.out.println("(8) Leave Rating"); 
            System.out.println("(9) Quit");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Enter your choice:");
            choices= scanner.nextInt();
            switch (choices) {
                case 1:
                    System.out.println("(1) List movies available");
                    //get
                    //function to show get availble movies
                    break;
                case 2:
                    System.out.println("(2) Search movies available");
                    //searchMovie();
                    //search function
                    break;
                case 3:
                    System.out.println("(3) View Movie Details");
                    //get movie details
                    break;
                case 4:
                    System.out.println("(4) Check seat availability ");
                    showAvailableSeats();
                    //this function is currently static need to define classes first
                    break;
                case 5:
                    System.out.println("(5) Book and purchase ticket");
                    //function to book and purchase
                    break;
                case 6:
                    System.out.println("(6) Show booking history");
                    // function to get booing details
                    break;
                case 7:
                    System.out.println("(7) View Top 5 movies ranked by ticket sales ");
                    //function to get top 5
                    break;
                case 8:
                    System.out.println("(8) Leave Rating");
                    //leave ratings
                    break;
                case 9:
                    isStillrunning = false;
                    System.out.println("bye");
                    break;
                
            }
         }

    }
    public static void main(String[] args){
        int choice = 0;
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
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("adminLogin()");
                    break;
                case 2:
                    MovieGoerLogin();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("bye");
                    break;
            }



        }
    }
}
