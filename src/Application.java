import controller.*;
import entity.cinema.Cinema;
import entity.cinema.Showtime;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        Initialization init = Initialization.initProgram();
        CineplexManager cineplexManager = init.cineplexManager;
        ShowtimeManager showtimeManager = init.showtimeManager;
        MovieManager movieManager = init.movieManager;
        BookingManager bookingManager = init.bookingManager;

        System.out.printf("Welcome to my moviplex!\n");

        init.saveProgram();
    }
}