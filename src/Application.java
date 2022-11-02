import entity.booking.PersonAge;
import entity.cinema.*;
import entity.movie.Movie;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.printf("Welcome to my moviplex!\n");
        Cineplex gvJurong = new Cineplex();
        Layout layout = new Layout(3,5);
        layout.setObject(0,0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0,1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0,4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(1,2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2,0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2,1, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2,3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));

        Cinema cinema = new Cinema("JR1".toCharArray(), layout);
        gvJurong.addCinema(cinema);
        Movie movie = new Movie();
        Showtime showtime = new Showtime(LocalDateTime.now(), cinema, movie);
        Scanner sc = new Scanner(System.in);
        int column = -1;
        int row = -1;
        String userInput = "Y";
        while (userInput != "N") {
            showtime.displaySeating();
            System.out.printf("Row : ");
            userInput = sc.nextLine();
            row = Integer.parseInt(userInput);
            System.out.printf("Column : ");
            userInput = sc.nextLine();
            column = Integer.parseInt(userInput);
            showtime.produceTicket(PersonAge.CHILD, row, column);
            showtime.displaySeating();
            System.out.printf("Would you like to buy more? (Y/N) ");
            userInput = sc.nextLine();
        }
    }
}