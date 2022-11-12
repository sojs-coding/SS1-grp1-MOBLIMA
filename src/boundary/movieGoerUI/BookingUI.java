package boundary.movieGoerUI;
import java.util.Scanner;

import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;

import controller.BookingManager;
import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import controller.TicketPriceManager;
import entity.booking.*;
import entity.cinema.*;
import entity.movie.Movie;
import entity.ticket.Ticket;
import entity.ticket.TicketType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookingUI {
    private MovieGoer movieGoer;
    private MovieManager movieManager;
    private CineplexManager cineplexManager;
    private ShowtimeManager showtimeManager;
    private BookingManager bookingManager;
    private TicketPriceManager ticketPriceManager;
    public static int avail = 0;

    public BookingUI(MovieGoer movieGoer, MovieManager m, CineplexManager c, ShowtimeManager showtimeManager, BookingManager b, TicketPriceManager t){
        this.movieGoer = movieGoer;
        this.movieManager = m;
        this.cineplexManager = c;
        this.showtimeManager = showtimeManager;
        this.bookingManager = b;
        this.ticketPriceManager = t;
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

    public static ArrayList<Showtime> printShowTimes(Cineplex cineplex, Movie movie, ShowtimeManager s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");
        ArrayList<Showtime> showTimes = s.getShowtimes();
        ArrayList<Showtime> wantedShowTimes = new ArrayList<Showtime>();
        int index = 0;
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Showtimes List                             ");
        System.out.println("--------------------------------------------------------------------");

        while (i < showTimes.size()){
            if (showTimes.get(i).getMovie() == movie && inCineplex(showTimes.get(i).getCinema(),cineplex)){
                System.out.println(index+1 + ") " + "Date: " + dtf.format(showTimes.get(i).getDateTime())+ ", Cinema: " + new String(showTimes.get(i).getCinema().getCode()));
                wantedShowTimes.add(showTimes.get(i));
                index++;
            }
            i++;
        }


        System.out.println("--------------------------------------------------------------------");
        return wantedShowTimes;
    }

    private Movie movieSearch(){
        Scanner sc = new Scanner(System.in);
        Movie movie = new Movie(null, null, null, 0, null, null, null);
        try {
            printMovies(movieManager);
            System.out.println("(1) Which movie are you watching?");
            System.out.println("Enter the title in full");
            String title = sc.nextLine();
            movie = movieManager.searchMovie(title);     
            if(movie == null){
                System.out.println("Movie does not exist...");
                return null;
            }
            else{
                return movie;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid entry...");
            sc.close();
            return null;
        }
    }

    private Cineplex cineplexSearch(){
        Scanner sc = new Scanner(System.in);
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
                return null;
            }
            else{
                return cineplex;
            }
        } catch (Exception e) {
            System.out.println("Invalid entry...");
            sc.close();
            return null;
        }
    }

    private ArrayList<Showtime> dateTimeSearch(Cineplex cineplex,Movie movie){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");

        try {
            ArrayList<Showtime> wantedShowTimes = printShowTimes(cineplex,movie,showtimeManager);
            if (wantedShowTimes.size() == 0){
                System.out.println("There are no showtimes...");
                return null;
            } //ShowTimes corresponding to cinema and movie are given
            System.out.println("(3) What and which cinema do you prefer? Choose the index (1,2 etc)");
            avail = sc.nextInt();
            return wantedShowTimes;
        } catch (Exception e) {
            System.out.println("Invalid entry...");
            sc.close();
            return null;
        }
    }

    private void DeleteCart(Showtime showtime, ArrayList<Ticket> tickets){
        Layout layout = showtime.getLayout();
        //Free the seats in cart
        System.out.println("Removing tickets from cart...");
        for(int i = 0; i < tickets.size(); i++){
            int row = tickets.get(i).getRow();
            int column = tickets.get(i).getColumn();
            layout.free(row,column);
        }
        return;
    }

    private Showtime findDateTime(ArrayList<Showtime> wantedShowTimes){
        Showtime showtime = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");

        for(int i = 0; i < wantedShowTimes.size(); i++){
            if (avail == i+1){
                showtime = wantedShowTimes.get(i);
                return showtime;
            }
        }

        if(showtime == null){
            System.out.println("Invalid Entry");
            System.out.println("Returning...");
            return null;
        }

        return null;
    }

    private TicketType IdentifyAge(String age){
        if(age == "STUDENT"){
            return TicketType.STUDENT;
        }
        return TicketType.ELDERLY;
    }

    private void PurchaseUI(Showtime showtime, Cinema cinema, Movie movie){
        Scanner sc = new Scanner(System.in);
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
                Scanner sc2 = new Scanner(System.in);
                String age;
                System.out.println("Are you a student or elderly?");
                age = sc2.nextLine();
                if (age instanceof String && (age.toUpperCase() == "ELDERLY" || age.toUpperCase() == "STUDENT")){
                    System.out.println("Invalid Entry...");
                    System.out.println("Returning...");
                    return;
                }
                age = age.toUpperCase();
                ArrayList<Ticket> tickets = new ArrayList<Ticket>();
                try {
                    while (true){
                        showtime.displaySeating();
                        Layout layout = showtime.getLayout();
                        System.out.println("Enter the seat (row column) you want to purchase (-1 to proceed booking, -2 to return to home menu)");
                        System.out.printf("Current tickets in cart: %d\n", tickets.size());
                        int row = sc2.nextInt();
                        if(row == -1)
                            break;
                        if(row < -1){
                            System.out.println("Exiting...");
                            DeleteCart(showtime, tickets);
                        }
                        int column = sc2.nextInt();
                        if (!layout.isOccupied(row, column)){
                            //Couple Seat
                            if(layout.getLayoutObject()[row][column].getSeatSymbol()== 'C'){
                                System.out.println("Couple seats are available.\n");
                                ArrayList<TicketType> ticketTypes = new ArrayList<>();
                                ticketTypes.add(IdentifyAge(age));
                                tickets.add(showtime.produceTicket(row, column, ticketTypes));
                            }
                            //Single seat
                            else{
                                System.out.println("Single seat is available.\n");
                                ArrayList<TicketType> ticketTypes = new ArrayList<>();
                                ticketTypes.add(IdentifyAge(age));
                                tickets.add(showtime.produceTicket(row, column, ticketTypes));
                                cinema.cloneCinemaLayout();
                            }
                        }
                        else{
                            System.out.println("Seat is taken.\n");
                        }
                    }
                    // Generates only one payment per MovieGoer
                    Payment payment = new Payment(cinema.getCode());
                    Booking booking = new Booking(showtime, movieGoer, payment, tickets, bookingManager.getIDCount());
                    bookingManager.getBookings().add(booking);
                    Paying(bookingManager.getIDCount());
                    bookingManager.setIDCount(bookingManager.getIDCount()+1);
                    return;

                } catch (Exception e) {
                    System.out.println("Invalid entry...");
                    DeleteCart(showtime, tickets);
                }

            default:
                System.out.println("Invalid entry...");
                System.out.println("Returning...");
                return;
        }
    }

   private void Paying(int bookingID){
        //Loop through specific booking and get the showtime, movieGoer, ticket
        System.out.println("---------------------------------------");
        System.out.println("Booking Summary:");
        for(int i = 0; i < bookingManager.getBooking(bookingID).getTicket().size(); i++){
            Showtime showtime = bookingManager.getBooking(bookingID).getCentral();
            Ticket ticket = bookingManager.getBooking(bookingID).getTicket().get(i);
            System.out.println("---------------------------------------");
            System.out.println("Ticket " + (i+1));
            System.out.println("Ticket Type: "+ticket.getTicketTypes());
            if (showtime.getLayout().getLayoutObject()[ticket.getRow()][ticket.getColumn()].getSeatSymbol()=='C'){
                System.out.println("Ticket Price: "+ ticketPriceManager.getPrice(ticket) + " * 2 = " + ticketPriceManager.getPrice(ticket)*2);
                ticket.setPrice(ticketPriceManager.getPrice(ticket)*2);
            }
            else{
                System.out.println("Ticket Price: "+ ticketPriceManager.getPrice(ticket));
                ticket.setPrice(ticketPriceManager.getPrice(ticket));
            }
            
        }
        System.out.println("---------------------------------------");    
    }
    
    public void BookAndPurchase(){
        Scanner sc = new Scanner(System.in);
        //Returns movie based on title
        Movie movie = movieSearch();
        if(movie == null)
            return;

        //Returns cineplex 
       Cineplex cineplex = cineplexSearch();
       if(cineplex == null)
            return;

        //Returns dateTime based on string given
        ArrayList<Showtime> wantedShowtimes = dateTimeSearch(cineplex, movie);
        if(wantedShowtimes == null)
            return;
        

        // Loops through Showtime array to find corresponding showtime with input datetime
        //Dummy cinema and showtime
        Showtime showtime = findDateTime(wantedShowtimes);
        if(showtime == null)
            return;
        Cinema cinema = showtime.getCinema();
        
        //PurchasingUI
        PurchaseUI(showtime, cinema, movie);
    }


    public void BookingHistory(){
        System.out.println("Printing your booking history");
        ArrayList<Booking> bookings = bookingManager.getBookings();
        int i = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");

        //Iterates through all bookings and print those that match movieGoer
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("                           Bookings of %s                            \n",movieGoer.getName());
        System.out.println("--------------------------------------------------------------------");
        while (i < bookings.size()){ //Find B
            if(bookings.get(i).getUser() == movieGoer){
                System.out.println("Booking ID: "+ bookings.get(i).getId());
                Booking temp = bookings.get(i);
                System.out.println();
                for(int j = 0; j < temp.getTicket().size(); j++){
                    Ticket individual = temp.getTicket().get(j);

                    if (temp.getUser().getName() == movieGoer.getName() && 
                    temp.getUser().getMobile() == movieGoer.getMobile() && 
                    temp.getUser().getEmail() == movieGoer.getEmail())
                        System.out.print("Cinema Code: ");
                        System.out.println(temp.getCentral().getCinema().getCode());
                        System.out.print("Movie Title: ");
                        System.out.println(temp.getCentral().getMovie().getTitle());
                        System.out.print("Date and Time of Movie: ");
                        System.out.println(dtf.format(temp.getCentral().getDateTime()));
                        System.out.print("Payment Tid: ");
                        System.out.println(temp.getPayment().getTid());
                        //Print both seats for couple seat
                        if(temp.getCentral().getLayout().getLayoutObject()[individual.getRow()][individual.getColumn()].getSeatSymbol() == 'C'){
                            System.out.print("(Couple Seats) Ticket rows and columns: ");
                            System.out.print(individual.getRow() + " " + individual.getColumn() + ", ");
                            if(i+1 < bookings.size() && temp.getCentral().getLayout().getLayoutObject()[individual.getRow()][individual.getColumn()+1].isOccupied())
                                System.out.print(individual.getRow() + " " + ((individual.getColumn())+1) + "\n");
                            else
                                System.out.print(individual.getRow() + " " + ((individual.getColumn())-1) + "\n");
                        }
                        else{
                            System.out.print("(Single Seat) Ticket row and column: ");
                            System.out.println(individual.getRow() + " " + individual.getColumn());
                        }
                        System.out.println("Price: " + individual.getPrice());
                        System.out.println("--------------------------------------------------------------------");
                    }
                    i++;
                    System.out.printf("                      Total Price: %.2f                            \n",temp.calculateTotalPrice());
                    System.out.println("--------------------------------------------------------------------\n");
                }
            }
        }

    public void TopFiveMovie(){
        /*Loop through all tickets in all bookings and keep count of movie using dict
         since each booking equals one ticket*/
        ArrayList<Booking> bookings = bookingManager.getBookings();
        int size = bookings.size();
        int i = 0;
        Map<String,Integer> movieCount = new Hashtable<String,Integer>();

        //Create a dictionary to keep count of each movie of the tickets
        while (i < size){
            String movieTitle = bookings.get(i).getCentral().getMovie().getTitle();
            if(((Hashtable<String, Integer>) movieCount).containsKey(movieTitle)){
                movieCount.put(movieTitle,movieCount.get(movieTitle)+bookings.get(i).getTicket().size());
            }
            else{
                movieCount.put(movieTitle,bookings.get(i).getTicket().size());
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
