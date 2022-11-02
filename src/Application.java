import controller.BookingManager;
import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import entity.cinema.Cinema;
import entity.cinema.Showtime;

import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        Initialization init = Initialization.initProgram();
        CineplexManager cineplexManager = init.cineplexManager;
        ShowtimeManager showtimeManager = init.showtimeManager;
        MovieManager movieManager = init.movieManager;
        BookingManager bookingManager = init.bookingManager;

        System.out.printf("Welcome to my moviplex!\n");
//        Cinema cinema = cineplexManager.getCineplexes().get(0).findCinema("JR1".toCharArray());
//        Showtime showtime = new Showtime(LocalDateTime.now(), cinema, movieManager.getMovie(0));
//        showtimeManager.addShowtime(showtime);
        Showtime showtime = showtimeManager.getShowtime(0);
        Cinema cinema = showtime.getCinema();
        cinema.code = new char[]{'a','b','c'};


        //Cinema cinemaChanged = cineplexManager.getCineplexes().get(0).findCinema("abc".toCharArray());
        init.saveProgram();
    }
}