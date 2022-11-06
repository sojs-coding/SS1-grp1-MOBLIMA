package boundary.movieGoerUI;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import controller.BookingManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import entity.MovieGoer;
import entity.booking.Booking;
import entity.booking.Payment;
import entity.booking.Ticket;
import entity.cinema.Cinema;
import entity.cinema.Cineplex;
import entity.cinema.Layout;
import entity.cinema.Showtime;
import entity.movie.Movie;
import java.util.ArrayList;

public class bookingUI {
    private MovieGoer movieGoer;

    public bookingUI(MovieGoer movieGoer){
        this.movieGoer = movieGoer;
    }

    public static void printMovies(){
        MovieManager MovieManager = new MovieManager();
        ArrayList<Movie> movies = MovieManager.getMovies();
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Movie List                             ");
        System.out.println("--------------------------------------------------------------------");
        while (i < movies.size()){
            System.out.println(movies.get(i).getTitle());
            i++;
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void printCinemas(){
        Cineplex cineplex = new Cineplex();
        ArrayList<Cinema> cinemas = cineplex.getCinemas();
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Cinemas List                             ");
        System.out.println("--------------------------------------------------------------------");
        while (i < cinemas.size()){
            System.out.println(cinemas.get(i).getCode());
            i++;
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void printShowTimes(Cinema cinema, Movie movie){
        ShowtimeManager showTime = new ShowtimeManager();
        ArrayList<Showtime> showTimes = showTime.getShowtimes();
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Showtimes List                             ");
        System.out.println("--------------------------------------------------------------------");
        while (i < showTimes.size()){
            if (showTimes.get(i).getCinema() == cinema && showTimes.get(i).getMovie() == movie){
                System.out.println(showTimes.get(i).getDateTime());
                i++;
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public void BookAndPurchase(){
        Scanner sc = new Scanner(System.in);
        MovieManager MovieManager = new MovieManager();
        Cineplex Cineplex = new Cineplex();
        ShowtimeManager ShowtimeManager = new ShowtimeManager();

        //Returns movie based on title
        printMovies();
        System.out.println("(1) Which movie are you watching?");
        System.out.println("Enter the title in full");
        String title = sc.nextLine();
        Movie movie = MovieManager.searchMovie(title);

        //Returns cinema based on code
        printCinemas();
        System.out.println("(2) Which cinema are you going to?");
        System.out.println("Enter the three character code");
        char[] code = {};
        for(int i = 0; i < 3; i++){
            code[i] = sc.next().charAt(0);
        }
        Cinema cinema = Cineplex.findCinema(code);

        //Returns dateTime based on string given
        printShowTimes(cinema,movie); //ShowTimes corresponding to cinema and movie are given
        System.out.println("(3) What are the dates and times that you prefer?");
        System.out.println("Enter the time in full");
        String avail = sc.nextLine();

        // Loops through Showtime array to find corresponding showtime with input datetime
        ArrayList<Showtime> showtimes= ShowtimeManager.getShowtimes();
        Showtime showtime = new Showtime(null, cinema, movie);
        LocalDateTime dateTime;
        for(int i = 0; i < showtimes.size(); i++){
            if (showtimes.get(i).getDateTime().toString() == avail){
                showtime = ShowtimeManager.getShowtime(i);
                dateTime = showtime.getDateTime();
                break;
            }
            else{
                System.out.println("Invalid date and time entry...");
                System.out.println("Returning...");
                return;
            }
        }
        
        //Prints seating arrangements
        showtime.displaySeating();
        System.out.println("The available seats are as shown above.");
        System.out.println("The ones with X are taken.");

        //Asks if they want to purchase tickets
        System.out.println("Do you want to purchase tickets?");
        System.out.println("0 for No, 1 for Yes");
        int choice = sc.nextInt();
        switch(choice){
            case 0:
                System.out.println("Returning to menu....");
                return;
            case 1:
                System.out.println("How many tickets are you purchasing?");
                int count = sc.nextInt();
                ArrayList<Ticket> tickets = new ArrayList<Ticket>();

                for(int i = 0; i < count; i++){
                    showtime.displaySeating();
                    Layout layout = showtime.getLayout();
                    System.out.printf("Please enter Seat %d that you want to purchase.", i);
                    int row = sc.nextInt();
                    int column = sc.nextInt();
                    if (!layout.occupy(row, column)){
                        System.out.println("Seat is available.");
                        tickets.add(new Ticket(row,column,movieGoer.getAge())); //Generates count number of tickets
                    }
                    else{
                        System.out.println("Seat is taken.");
                    }
                }
                // Generates only one payment per MovieGoer
                Payment payment = new Payment(cinema.getCode());
                BookingManager bookingManager = new BookingManager();
                for(int i = 0; i < count; i++){
                    Booking booking = new Booking(showtime, movieGoer, payment, tickets.get(i), i);
                    bookingManager.addBooking(booking);
                }

            default:
                System.out.println("Invalid entry...");
                System.out.println("Returning...");
                return;
        }
    }


    public void BookingHistory(MovieGoer user){
        System.out.println("Printing your booking history");
        BookingManager bookingManager = new BookingManager();
        ArrayList<Booking> bookings = bookingManager.getBookings();
        int i = 0;

        System.out.println("--------------------------------------------------------------------");
        System.out.printf("                           Bookings of %s                            \n",user.getName());
        System.out.println("--------------------------------------------------------------------");
        while (i < bookings.size()){
            if (bookings.get(i).getUser().getName() == user.getName() && 
            bookings.get(i).getUser().getMobile() == user.getMobile() && 
            bookings.get(i).getUser().getEmail() == user.getEmail()){
                System.out.print("Cinema Code: ");
                System.out.println(bookings.get(i).getCentral().getCinema().getCode());
                System.out.print("Movie Title: ");
                System.out.println(bookings.get(i).getCentral().getMovie().getTitle());
                System.out.print("Date and Time of Movie: ");
                System.out.println(bookings.get(i).getCentral().getDateTime());
                System.out.print("Payment Tid: ");
                System.out.println(bookings.get(i).getPayment().getTid());
                System.out.print("Ticket row and column: ");
                System.out.println(bookings.get(i).getTicket().getRow() + " " + bookings.get(i).getTicket().getColumn());
        
            }
        }
    }
}
