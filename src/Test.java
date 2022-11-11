import controller.*;
import entity.cinema.Cinema;
import entity.cinema.Showtime;
import entity.movie.Movie;
import entity.ticket.Ticket;
import entity.ticket.TicketType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Initialization.initProgram();
        Initialization init = Initialization.getINSTANCE();
        ShowtimeManager showtimeManager = init.getShowtimeManager();
        CineplexManager cineplexManager = init.getCineplexManager();
        Cinema cinema = cineplexManager.getCineplexes().get(0).getCinema(0);
        MovieManager movieManager = init.getMovieManager();
        Movie movie = movieManager.searchMovie("Scream");
        TicketPriceManager ticketPriceManager = init.getTicketPriceManager();
        showtimeManager.addShowtime(
                new Showtime(
                        LocalDateTime.parse("2022-11-12 13:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        cinema,
                        movie
                )
        );
        cinema = cineplexManager.getCineplexes().get(1).findCinema("WL3".toCharArray());
        showtimeManager.addShowtime(
                new Showtime(
                        LocalDateTime.parse("2022-11-17 19:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        cinema,
                        movie
                )
        );
        for (int i = 0; i < showtimeManager.getShowtimes().size(); i++)
        {
            Showtime showtime = showtimeManager.getShowtime(i);
            System.out.println(showtime.getMovie().getTitle());
            System.out.println(showtime.getCinema().getCinemaClass());
            System.out.println(showtime.getDateTime().getDayOfWeek());
            System.out.println();

            showtime.displaySeating();
            ArrayList<TicketType> ticketTypes = new ArrayList<>();
            ticketTypes.add(TicketType.STUDENT);
            Ticket ticket = showtime.produceTicket(1,1, ticketTypes);
            System.out.println(ticket.getTicketTypes());
            System.out.println(ticketPriceManager.getPrice(ticket));
            showtime.displaySeating();
            System.out.println();
            System.out.println();
        }
    }
}