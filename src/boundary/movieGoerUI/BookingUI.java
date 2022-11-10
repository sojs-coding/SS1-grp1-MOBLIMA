package boundary.movieGoerUI;
import java.util.Scanner;

import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;

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
import entity.cinema.LayoutObject;
import entity.cinema.Showtime;
import entity.movie.Movie;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookingUI {
    private MovieGoer movieGoer;
    private MovieManager movieManager;
    private CineplexManager cineplexManager;
    private ShowtimeManager showtimeManager;
    private Layout layout;
    private BookingManager bookingManager;

    public BookingUI(MovieGoer movieGoer, MovieManager m, CineplexManager c, ShowtimeManager showtimeManager,Layout l,BookingManager b){
        this.movieGoer = movieGoer;
        this.movieManager = m;
        this.cineplexManager = c;
        this.showtimeManager = showtimeManager;
        this.layout = l;
        this.bookingManager = b;
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

    public static int printShowTimes(Cineplex cineplex, Movie movie, ShowtimeManager s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYMMddHHmm");
        ArrayList<Showtime> showTimes = s.getShowtimes();
        int count = 0;
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Showtimes List                             ");
        System.out.println("--------------------------------------------------------------------");

        while (i < showTimes.size()){
            if (showTimes.get(i).getMovie() == movie && inCineplex(showTimes.get(i).getCinema(),cineplex)){
                System.out.println("Date: " + dtf.format(showTimes.get(i).getDateTime())+ ", Cinema: " + new String(showTimes.get(i).getCinema().getCode()));
                count++;
            }
            i++;
        }


        System.out.println("--------------------------------------------------------------------");
        return count;
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
            if(movie == null){
                System.out.println("Movie does not exist...");
                return;
            }
        } catch (NoSuchElementException e) {
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
            if(cineplex == null){
                System.out.println("Cineplex does not exist...");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid entry...");
            sc.close();
            return;
        }

        //Returns dateTime based on string given
        String avail;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYMMddHHmm");
        try {
            
            int count = printShowTimes(cineplex,movie,showtimeManager);
            if (count == 0){
                System.out.println("There are no showtimes...");
                return;
            } //ShowTimes corresponding to cinema and movie are given
            System.out.println("(3) What and which cinema do you prefer? YYTTMMddHHmm format");
            System.out.println("Enter the time and cinema...(E.g 202209011122 JR1)");
            avail = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid entry...");
            sc.close();
            return;
        }
        

        // Loops through Showtime array to find corresponding showtime with input datetime
        Cinema cinema = new Cinema("JR1".toCharArray(),layout);
        Showtime showtime = new Showtime(null, cinema, null);
        ArrayList<Showtime> showtimes = showtimeManager.getShowtimes();

        for(int i = 0; i < showtimes.size(); i++){
            String code = new String(showtimes.get(i).getCinema().getCode());
            if (avail.equals(new String(dtf.format(showtimes.get(i).getDateTime()) + " " + code))){
                showtime = showtimes.get(i);
                cinema = showtimeManager.getShowtime(i).getCinema();
                break;
            }
        }

        //if no there is no showtime with corresonding dateTime we return back to movieGoerUI
        if(showtime.getDateTime() == null){
            System.out.println("Invalid date and time entry...");
            System.out.println("Returning...");
            return;
        }
        
        //Prints seating arrangements
        showtime.displaySeating();
        System.out.println("The available seats are as shown above.");
        System.out.println("The ones with X are taken.");

        //Asks if they want to purchase tickets
        System.out.println("Do you want to purchase tickets?");
        System.out.println("1 for No, 2 for Yes");
        int choice;
        choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Returning to menu....");
                return;
            case 2:
                ArrayList<Ticket> tickets = new ArrayList<Ticket>();
                try {
                    while (true){
                        showtime.displaySeating();
                        Layout layout = showtime.getLayout();
                        System.out.println("Enter the seat (row column) you want to purchase (-1 to proceed booking, -2 to return to home menu)");
                        System.out.printf("Current tickets in cart: %d\n", tickets.size());
                        int row = sc.nextInt();
                        if(row == -1)
                            break;
0                        if(row < -1){
                            System.out.println("Exiting...");

                            //Free the seats in cart
                            System.out.println("Removing tickets from cart...");
                            for(int k = 0; k < tickets.size(); k++){
                                int r = tickets.get(k).getRow();
                                int column = tickets.get(k).getColumn();
                                layout.free(r,column);
                            }
                            return;
                        }
                        int column = sc.nextInt();
                        if (!layout.isOccupied(row, column)){
                            //Couple seat
                            if(layout.getLayoutObject()[row][column].getSeatSymbol()== 'C'){
                                System.out.println("Couple seats are available.\n");
                                tickets.add(new Ticket(row,column,movieGoer.getAge())); 
                                layout.occupy(row, column);
                            }
                            //Single seat
                            else{
                                System.out.println("Single seat is available.\n");
                                tickets.add(new Ticket(row,column,movieGoer.getAge())); 
                                layout.occupy(row, column);
                            }
                            //Generates count number of tickets
                        }
                        else{
                            System.out.println("Seat is taken.\n");
                        }
                    }
                    // Generates only one payment per MovieGoer
                    Payment payment = new Payment(cinema.getCode());
                    for(int j = 0; j < tickets.size(); j++){
                        Booking booking = new Booking(showtime, movieGoer, payment, tickets.get(j), j);
                        bookingManager.addBooking(booking);
                    }
                    return;

                } catch (Exception e) {
                    System.out.println("Invalid entry...");

                    //Free the seats in cart
                    System.out.println("Removing tickets from cart...");
                    for(int i = 0; i < tickets.size(); i++){
                        int row = tickets.get(i).getRow();
                        int column = tickets.get(i).getColumn();
                        layout.free(row,column);
                    }
                    return;
                }

            default:
                System.out.println("Invalid entry...");
                System.out.println("Returning...");
                return;
        }
    }


    public void BookingHistory(){
        System.out.println("Printing your booking history");
        ArrayList<Booking> bookings = bookingManager.getBookings();
        int i = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYMMddHHmm");

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
                System.out.println(dtf.format(bookings.get(i).getCentral().getDateTime()));
                System.out.print("Payment Tid: ");
                System.out.println(bookings.get(i).getPayment().getTid());
                //Print both seats for couple seat
                if(bookings.get(i).getCentral().getLayout().getLayoutObject()[bookings.get(i).getTicket().getRow()][bookings.get(i).getTicket().getColumn()].getSeatSymbol() == 'C'){
                    System.out.print("(Couple Seats) Ticket rows and columns: ");
                    System.out.print(bookings.get(i).getTicket().getRow() + " " + bookings.get(i).getTicket().getColumn() + ", ");
                    if(bookings.get(i).getCentral().getLayout().getLayoutObject()[bookings.get(i).getTicket().getRow()][bookings.get(i).getTicket().getColumn()+1].isOccupied())
                        System.out.print(bookings.get(i).getTicket().getRow() + " " + ((bookings.get(i).getTicket().getColumn())+1) + "\n");
                    else
                        System.out.print(bookings.get(i).getTicket().getRow() + " " + ((bookings.get(i).getTicket().getColumn())-1) + "\n");
                }
                System.out.print("(Single Seat) Ticket row and column: ");
                System.out.println(bookings.get(i).getTicket().getRow() + " " + bookings.get(i).getTicket().getColumn());
            }
            i++;
            System.out.println("--------------------------------------------------------------------\n");
        }
    }

    public void TopFiveMovie(){
        /*Loop through all bookings and keep count of movie using dict
         since each booking equals one ticket*/
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
        System.out.println("--------------------------------------------------------------------\n");
    }
}
