package boundary.movieGoerUI;
import java.util.Scanner;

import javax.management.AttributeNotFoundException;

import java.util.Hashtable;
import java.util.Map;

import controller.BookingManager;
import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import entity.booking.MovieGoer;
import entity.booking.Booking;
import entity.booking.Payment;
import entity.booking.Ticket;
import entity.cinema.Cinema;
import entity.cinema.Cineplex;
import entity.cinema.Layout;
import entity.cinema.Showtime;
import entity.movie.Movie;
import java.util.ArrayList;

public class BookingUI {
    private MovieGoer movieGoer;
    private MovieManager movieManager;
    private CineplexManager cineplexManager;
    private ShowtimeManager showtimeManager;

    public BookingUI(MovieGoer movieGoer, MovieManager m, CineplexManager c, ShowtimeManager showtimeManager){
        this.movieGoer = movieGoer;
        this.movieManager = m;
        this.cineplexManager = c;
        this.showtimeManager = showtimeManager;
    }

    public String getMaxCount(Map<String,Integer> movieCount){
        int max = 0;
        String maxString = "";

        for(Map.Entry<String,Integer> entry : movieCount.entrySet()){
            String key = entry.getKey();
            int val = entry.getValue();

            if (val > max){
                maxString = key;
                max = val;
            }
        }

        return maxString;
    }

    public static void printMovies(MovieManager movieManager){
        ArrayList<Movie> movies = movieManager.getMovies();
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

    public static void printCineplex(CineplexManager cineplex){
        ArrayList<Cineplex> c = cineplex.getCineplexes();
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Cineplex List                            ");
        System.out.println("--------------------------------------------------------------------");
        while (i < c.size()){
            System.out.println(c.get(i).getCompany());
            i++;
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static boolean inCineplex(Cinema c, Cineplex cin){
        ArrayList<Cinema> cinemas = cin.getCinemas();
        for(int i = 0 ; i < cinemas.size(); i++){
            if(cinemas.get(i).getCode() == c.getCode())
                return true;
        }

        return false;
    }

    public static void printShowTimes(Cineplex cineplex, Movie movie, ShowtimeManager s){
    
        ArrayList<Showtime> showTimes = s.getShowtimes();
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Showtimes List                             ");
        System.out.println("--------------------------------------------------------------------");
        while (i < showTimes.size()){
            if (showTimes.get(i).getMovie() == movie && inCineplex(showTimes.get(i).getCinema(),cineplex)){
                System.out.println("Date: " + showTimes.get(i).getDateTime().toString() + ", Cinema: " + showTimes.get(i).getCinema().getCode().toString());
                i++;
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public void BookAndPurchase(){
        Scanner sc = new Scanner(System.in);

        //Returns movie based on title
        Movie movie = new Movie(null, null, null, 0, null, null, null);
        try {
            printMovies(movieManager);
            System.out.println("(1) Which movie are you watching?");
            System.out.println("Enter the title in full");
            String title = sc.nextLine();
            movie = movieManager.searchMovie(title);
        } catch (Exception e) {
            System.out.println("Invalid entry...");
            sc.close();
            return;
        }

        //Returns cineplex 
        Cineplex cineplex = new Cineplex(null);
        String cineplexString;
        try {
            printCineplex(cineplexManager);
            System.out.println("(2) Which cineplex are you going to?");
            System.out.println("Enter the title of the cineplex...");
            cineplexString = sc.nextLine();
            cineplex = cineplexManager.searchCompany(cineplexString);
        } catch (Exception e) {
            System.out.println("Invalid entry...");
            sc.close();
            return;
        }

        //Returns dateTime based on string given
        String avail;
        try {
            printShowTimes(cineplex,movie,showtimeManager); //ShowTimes corresponding to cinema and movie are given
            System.out.println("(3) What are the dates and times that you prefer?");
            System.out.println("Enter the time in full");
            avail = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid entry...");
            sc.close();
            return;
        }
        

        // Loops through Showtime array to find corresponding showtime with input datetime
        Cinema cinema = new Cinema(null, null);
        Showtime showtime = new Showtime(null, null, movie);
        for(int i = 0; i < showtimeManager.getShowtimes().size(); i++){
            if (showtimeManager.getShowtimes().get(i).getDateTime().toString() == avail){
                showtime = showtimeManager.getShowtime(i);
                cinema = showtimeManager.getShowtime(i).getCinema();
                break;
            }
        }

        //if no there is no showtime with corresonding dateTime we return back to movieGoerUI
        if(showtime.getDateTime() == null){
            System.out.println("Invalid date and time entry...");
            System.out.println("Returning...");
            sc.close();
            return;
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
                sc.close();
                return;
            case 1:
                try {
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
                    sc.close();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid entry...");
                    sc.close();
                    return;
                }

            default:
                System.out.println("Invalid entry...");
                System.out.println("Returning...");
                sc.close();
                return;
        }
        sc.close();
    }


    public void BookingHistory(){
        System.out.println("Printing your booking history");
        BookingManager bookingManager = new BookingManager();
        ArrayList<Booking> bookings = bookingManager.getBookings();
        int i = 0;

        //Iterates through all bookings and print those that match movieGoer
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("                           Bookings of %s                            \n",movieGoer.getName());
        System.out.println("--------------------------------------------------------------------");
        while (i < bookings.size()){
            if (bookings.get(i).getUser().getName() == movieGoer.getName() && 
            bookings.get(i).getUser().getMobile() == movieGoer.getMobile() && 
            bookings.get(i).getUser().getEmail() == movieGoer.getEmail()){
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
            i++;
        }
    }

    public void TopFiveMovie(){
        /*Loop through all bookings and keep count of movie using dict
         since each booking equals one ticket*/

        BookingManager bookingManager = new BookingManager();
        ArrayList<Booking> bookings = bookingManager.getBookings();
        int size = bookings.size();
        int i = 0;
        Map<String,Integer> movieCount = new Hashtable<String,Integer>();

        //Create a dictionary to keep count of each movie of the tickets
        while (i < size){
            String movieTitle = bookings.get(i).getCentral().getMovie().getTitle();
            if(((Hashtable<String, Integer>) movieCount).containsKey(movieTitle)){
                movieCount.put(movieTitle,movieCount.get(movieTitle)+1);
            }
            else{
                movieCount.put(movieTitle,1);
            }
            i++;
        } 

        //Find the max number of tickets with movie and print
        int count = 5;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                  Top 5 Movies (By Ticket Sales)                    ");
        System.out.println("--------------------------------------------------------------------");
        while (count > 0){
            if(!movieCount.isEmpty()){
                String topMovie = getMaxCount(movieCount);
                System.out.printf("(1): %s , Ticket Sales: %d\n",topMovie,movieCount.get(topMovie));
                movieCount.remove(topMovie);
            }
            else{
                System.out.println("There are less than 5 movies premiered...");
                break;
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }
}
