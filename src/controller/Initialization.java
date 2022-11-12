package controller;

import entity.ticket.*;
import entity.cinema.*;
import entity.movie.Movie;
import entity.movie.MovieType;
import entity.movie.ShowingStatus;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


/**
Represents the controller.Initialization of the application
 It shall be the central point of the application
 Provides all the necessary classes
 @author Samuel Ong
 @version 1.1
 @since 2022-11-02
 */
public class Initialization implements Serializable {
    /**
     * The only INSTANCE initialization should have
     */
    private static Initialization INSTANCE;
    /**
     * A manager to manage all the Cineplexes
     */
    private CineplexManager cineplexManager;
    /**
     * A manager to manage all the bookings
     */
    private BookingManager bookingManager;
    /**
     * A manager to manage all the movies
     */
    private MovieManager movieManager;
    /**
     * A manager to manage all the showtimes
     */
    private ShowtimeManager showtimeManager;

    /**
     * A manager to manage all the holidays
     */
    private HolidayManager holidayManager;

    /**
     * A manager to manage all the Ticket Prices
     */
    private TicketPriceManager ticketPriceManager;
 

    public CineplexManager getCineplexManager() {
        return cineplexManager;
    }

    public BookingManager getBookingManager() {
        return bookingManager;
    }

    public MovieManager getMovieManager() {
        return movieManager;
    }

    public ShowtimeManager getShowtimeManager() {
        return showtimeManager;
    }

    public HolidayManager getHolidayManager() {
        return holidayManager;
    }

    public TicketPriceManager getTicketPriceManager() {
        return ticketPriceManager;
    }

    /**
     * Constructor of initialization
     */
    Initialization() {
        if (Initialization.INSTANCE != null) {
            throw new ExceptionInInitializerError();
        }
        cineplexManager = new CineplexManager();
        bookingManager = new BookingManager();
        movieManager = new MovieManager();
        showtimeManager = new ShowtimeManager();
        holidayManager = new HolidayManager();
        ticketPriceManager = new TicketPriceManager(10);
    }

    /**
     * Instantiate a new controller.Initialization if null
     *
     * @return the only INSTANCE of initialization.
     */
    public static Initialization getINSTANCE() {
        if (Initialization.INSTANCE == null) {
            Initialization.INSTANCE = new Initialization();
        }
        return Initialization.INSTANCE;
    }

    /**
     * Load past data
     *
     * @return INSTANCE of controller.Initialization
     */
    public static Initialization initProgram() {
        boolean deleteFile = false;
        File f = new File("./cinemadata/init.ser");
        if (f.exists() && !f.isDirectory()) {
            FileInputStream fileIn = null;
            ObjectInputStream in = null;
            // do something
            try {
                fileIn = new FileInputStream("./cinemadata/init.ser");
                in = new ObjectInputStream(fileIn);
                Initialization.INSTANCE = (Initialization) in.readObject();
            } catch (Exception c) {
                deleteFile = true;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (fileIn != null) {
                    try {
                        fileIn.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (deleteFile) {
                f.delete();
                initProgram();
            } else {
                System.out.println("Loaded");
            }
        } else {
            if (Initialization.INSTANCE == null) {
                Initialization.INSTANCE = new Initialization();
            }
            Initialization.INSTANCE.initMovie();
            Initialization.INSTANCE.initCineplex();
            Initialization.INSTANCE.initHolidays();
            Initialization.INSTANCE.initTicketPrices();
            System.out.println("Initialized");
        }
        return Initialization.INSTANCE;
    }

    /**
     * Saves current data
     */
    public void saveProgram() {
        try {
            File file = new File("./cinemadata");
            if (!file.exists()) {
                file.mkdir();
            }
            FileOutputStream fileOut = new FileOutputStream("./cinemadata/init.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Initialization.INSTANCE);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in ./cinemadata/init.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Prepare fresh copy of Movies
     */
    private void initMovie() {
        String[] s = {"A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA. Before opening day, he invites a team of experts and his two eager grandchildren to experience the park and help calm anxious investors. However, the park is anything but amusing as the security systems go off-line and the dinosaurs escape. ",
                      "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods -- and imprisoned just as quickly -- Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                      "On Halloween night, Tara Heyes finds herself as the obsession of a sadistic murderer known as Art the Clown.", 
                      "A woman in New York, who seems to have things under control, is faced with a trauma that makes her life unravel.", 
                      "An aging Chinese immigrant is swept up in an insane adventure, in which she alone can save the world by exploring other universes connecting with the lives she could have led.",
                      "A grumpy widower whose only joy comes from criticizing and judging his exasperated neighbors meets his match when a lively young family moves in next door, leading to an unexpected friendship that will turn his world upside-down.",
                      "A wealthy New York City investment banking executive, Patrick Bateman, hides his alternate psychopathic ego from his co-workers and friends as he delves deeper into his violent, hedonistic fantasies.",
                      "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
                      "25 years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past.",
                      "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."}
     
        for(int count = 0; i < 10; count++){
            StringBuilder sb = new StringBuilder(s[i]);
            int i = 0;
            while (i + 40 < sb.length() && (i = sb.lastIndexOf(" ", i + 40)) != -1) {
                sb.replace(i, i + 1, "\n");
            }
     
        Movie movie1 = new Movie("Jurassic Park",
                ShowingStatus.NOW_SHOWING,
                s[0],
                0, MovieType.BLOCKBUSTER, "Me", new String[]{"Laura Dern", "Jeff Goldblum", "Richard Attenborough", "Bob Peck"});

        Movie movie2 = new Movie("Black Adam",
                ShowingStatus.NOW_SHOWING,
                s[1],
                4.5, MovieType.MOVIE2D, "Jaume Collet-Serra", new String[]{"Dwayne Johnson", "Aldis Hodge", "Noah Centineo", "Sarah Shahi", "Marwan Kenzari", "Quintessa Swindell"});

        Movie movie3 = new Movie("Terrifier",
                ShowingStatus.END_OF_SHOWING,
                s[2],
                2.6, MovieType.MOVIE3D, "Damien Leone", new String[]{"Jenna Kanell", "Samantha Scaffidi", "David Howard Thornton"});

        Movie movie4 = new Movie("Luckiest Girl Alive",
                ShowingStatus.NOW_SHOWING,
                s[3],
                3.2, MovieType.MOVIE2D, "Mike Barker", new String[]{"Mila Kunis", "Chiara Aurelia", "Finn Wittrock"});

        Movie movie5 = new Movie("Everything Everywhere All at Once",
                ShowingStatus.END_OF_SHOWING,
                s[4],
                3.2, MovieType.MOVIE2D, "Dan Kwan", new String[]{"Michelle Yeoh", "Stephanie Hsu", "Jamie Lee Curtis"});

        Movie movie6 = new Movie("A Man Called Otto",
                ShowingStatus.PREVIEW,
                s[5],
                0, MovieType.MOVIE2D, "Marc Forster", new String[]{"Tom Hanks", "Kailey Hyman", "Rachel Keller"});

        Movie movie7 = new Movie("American Psycho",
                ShowingStatus.END_OF_SHOWING,
                s[6],
                3.7, MovieType.BLOCKBUSTER, "Mary Harron", new String[]{"Christian Bale", "Justin Theroux", "Josh Lucas"});

        Movie movie8 = new Movie("Harry Potter and the Philosopher's Stone",
                ShowingStatus.END_OF_SHOWING,
                s[7],
                3.9, MovieType.MOVIE2D, "Chris Columbus", new String[]{"Daniel Radcliffe", "Rupert Grint", "Richard Harris"});

        Movie movie9 = new Movie("Scream",
                ShowingStatus.NOW_SHOWING,
                s[8],
                3.1, MovieType.MOVIE3D, "Matt Bettinelli-Olpin", new String[]{"Neve Campbell", "Courteney Cox", "David Arquette"});

        Movie movie10 = new Movie("Interstellar",
                ShowingStatus.END_OF_SHOWING,
                s[9],
                4.3, MovieType.BLOCKBUSTER, "Christopher Nolan", new String[]{"Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"});

        movieManager.addMovie(movie1);
        movieManager.addMovie(movie2);
        movieManager.addMovie(movie3);
        movieManager.addMovie(movie4);
        movieManager.addMovie(movie5);
        movieManager.addMovie(movie6);
        movieManager.addMovie(movie7);
        movieManager.addMovie(movie8);
        movieManager.addMovie(movie9);
        movieManager.addMovie(movie10);
    }

    private void initHolidays() {
        holidayManager.addDate("20221225");
    }

    private void initTicketPrices() {
        TicketRule temp;
        TicketRule ticketRule;
        TicketRule temp2;
        ArrayList<DayOfWeek> weekdays = new ArrayList<DayOfWeek>();
        weekdays.add(DayOfWeek.MONDAY);
        weekdays.add(DayOfWeek.TUESDAY);
        weekdays.add(DayOfWeek.WEDNESDAY);
        weekdays.add(DayOfWeek.THURSDAY);
        weekdays.add(DayOfWeek.FRIDAY);

        ArrayList<DayOfWeek> weekend = new ArrayList<DayOfWeek>();
        weekend.add(DayOfWeek.SATURDAY);
        weekend.add(DayOfWeek.SUNDAY);

        ArrayList<DayOfWeek> friNweekend = new ArrayList<DayOfWeek>();
        friNweekend.add(DayOfWeek.SATURDAY);
        friNweekend.add(DayOfWeek.SUNDAY);
        friNweekend.add(DayOfWeek.FRIDAY);

        ArrayList<DayOfWeek> monToThurs = new ArrayList<DayOfWeek>();
        monToThurs.add(DayOfWeek.MONDAY);
        monToThurs.add(DayOfWeek.TUESDAY);
        monToThurs.add(DayOfWeek.WEDNESDAY);
        monToThurs.add(DayOfWeek.THURSDAY);
        // Monday to Friday Before 6PM
        ticketRule = new TicketRule(7);
        ticketRule.addRule(new DateRule(weekdays));
        ticketRule.addRule(new Before6pmRule());
        // Student Monday to Friday Before 6PM
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.STUDENT));
        temp2 = TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(7);
        ticketPriceManager.addRule(temp2);
        temp2 = TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Elderly Monday to Friday Before 6PM
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.ELDERLY));
        temp2 = TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(5);
        ticketPriceManager.addRule(temp2);
        temp2 = TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // After 6PM Thursday
        ticketRule = new TicketRule(10);
        ticketRule.addRule(new After6pmRule());
        ticketRule.addRule(new DateRule(new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.THURSDAY))));
        // Student After 6PM Thursday
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.STUDENT));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(10);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Elderly After 6PM Thursday
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.ELDERLY));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(10);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // After 6PM Friday
        ticketRule = new TicketRule(14.5);
        ticketRule.addRule(new After6pmRule());
        ticketRule.addRule(new DateRule(new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.FRIDAY))));
        // Student After 6PM Friday
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.STUDENT));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Elderly After 6PM Friday
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.ELDERLY));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Saturday to Sunday
        ticketRule = new TicketRule(14.5);
        ticketRule.addRule(new DateRule(weekend));
        // Student Saturday to Sunday
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.STUDENT));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Elderly Saturday to Sunday
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.ELDERLY));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Eve of PH and PH
        ticketRule = new TicketRule(14.5);
        ticketRule.addRule(new PublicHolidayRule());
        // Eve of PH and PH
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.STUDENT));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Eve of PH and PH
        temp = TicketRule.copyTicketRule(ticketRule);
        temp.addRule(new TicketTypeRule(TicketType.ELDERLY));
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(16);
        ticketPriceManager.addRule(temp2);
        // Friday to Sunday
        ticketRule = new TicketRule(14.5);
        ticketRule.addRule(new DateRule(friNweekend));
        temp = ticketRule;
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(25);
        // Eve of PH and PH
        ticketRule = new TicketRule(14.5);
        ticketRule.addRule(new PublicHolidayRule());
        temp = ticketRule;
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(14.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(15.5);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(25);
        // Monday to Thursday
        ticketRule = new TicketRule(10);
        ticketRule.addRule(new DateRule(monToThurs));
        temp = ticketRule;

        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.NORMAL));
        temp2.setPrice(10);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.DOLBY_ATMOS_2D));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ULTIMA));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.ELITE_CLUB));
        temp2.setPrice(14);
        ticketPriceManager.addRule(temp2);
        temp2 =  TicketRule.copyTicketRule(temp);
        temp2.addRule(new CinemaClassRule(CinemaClass.PLATINUM_MOVIE_SUITES));
        temp2.setPrice(25);
    }

    /**
     * Prepare fresh copy of Cineplexes
     */
    private void initCineplex() {
        Cineplex gvJurong = new Cineplex("Jurong");
        Layout layout = new Layout(3, 5);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(1, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        Cinema cinema = new Cinema("JR1".toCharArray(), CinemaClass.NORMAL, layout);
        gvJurong.addCinema(cinema);


        showtimeManager.addShowtime(
                new Showtime(
                        LocalDateTime.parse("2022-11-17 19:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        cinema,
                        movieManager.getMovie(0)
                )
        );


        layout = new Layout(5, 5);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(4, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(4, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        cinema = new Cinema("JR2".toCharArray(), CinemaClass.ELITE_CLUB, layout);
        gvJurong.addCinema(cinema);
        cineplexManager.addCineplex(gvJurong);

        showtimeManager.addShowtime(
                new Showtime(
                        LocalDateTime.parse("2022-11-12 20:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        cinema,
                        movieManager.getMovie(0)
                )
        );

        layout = new Layout(5, 7);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 6, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 5, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 6, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 5, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 6, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 5, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));

        cinema = new Cinema("JR3".toCharArray(), CinemaClass.DOLBY_ATMOS_2D, layout);
        gvJurong.addCinema(cinema);

        showtimeManager.addShowtime(
            new Showtime(
                    LocalDateTime.parse("2022-11-17 14:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    cinema,
                    movieManager.getMovie(0)
            )
    );


        Cineplex gvWoodlands = new Cineplex("Woodlands");
        layout = new Layout(3, 5);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        cinema = new Cinema("WL1".toCharArray(),CinemaClass.PLATINUM_MOVIE_SUITES, layout);
        gvWoodlands.addCinema(cinema);


        //Showtime juraShowtime = new Showtime(LocalDateTime.now(),cinema,movieManager.getMovie(0));
        //showtimeManager.addShowtime(juraShowtime);

        layout = new Layout(3, 5);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(1, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        cinema = new Cinema("WL2".toCharArray(), CinemaClass.DOLBY_ATMOS_2D, layout);
        gvWoodlands.addCinema(cinema);

        layout = new Layout(3, 5);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(1, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(1, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        cinema = new Cinema("WL3".toCharArray(),CinemaClass.PLATINUM_MOVIE_SUITES, layout);
        gvWoodlands.addCinema(cinema);
        cineplexManager.addCineplex(gvWoodlands);

        Cineplex gvTampines = new Cineplex("Tampines");
        layout = new Layout(5, 7);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 6, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 5, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 6, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 5, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 6, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 5, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));

        cinema = new Cinema("TM1".toCharArray(),CinemaClass.ULTIMA, layout);
        gvTampines.addCinema(cinema);

        //Showtime juraShowtime = new Showtime(LocalDateTime.now(),cinema,movieManager.getMovie(0));
        //showtimeManager.addShowtime(juraShowtime);

        layout = new Layout(4, 6);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));

        cinema = new Cinema("TM2".toCharArray(),CinemaClass.ELITE_CLUB, layout);
        gvTampines.addCinema(cinema);

        layout = new Layout(5, 5);
        layout.setObject(0, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(4, 0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(4, 3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4, 4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        cinema = new Cinema("TM3".toCharArray(),CinemaClass.DOLBY_ATMOS_2D, layout);
        gvTampines.addCinema(cinema);
        cineplexManager.addCineplex(gvTampines);

    }
    


}
