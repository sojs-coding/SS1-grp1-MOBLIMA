package controller;

import entity.cinema.*;
import entity.movie.Movie;
import entity.movie.MovieType;
import entity.movie.ShowingStatus;
import java.io.*;

/**
Represents the controller.Initialization of the application
 It shall be the central point of the application
 Provides all the necessary classes
 @author Samuel Ong
 @version 1.1
 @since 2022-11-02
 */
public class Initialization implements Serializable{
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
    }

    /**
     * Instantiate a new controller.Initialization if null
     * @return the only INSTANCE of initialization.
     */
    public static Initialization getINSTANCE() {
        if (Initialization.INSTANCE == null)
        {
            Initialization.INSTANCE = new Initialization();
        }
        return Initialization.INSTANCE;
    }

    /**
     * Load past data
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
            }
            finally {
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
    public void initMovie() {
        Movie movie1 = new Movie("Jurassic Park",
                ShowingStatus.NOW_SHOWING,
                "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA. Before opening day, he invites a team of experts and his two eager grandchildren to experience the park and help calm anxious investors. However, the park is anything but amusing as the security systems go off-line and the dinosaurs escape. ",
                0, MovieType.BLOCKBUSTER, "Me", new String[]{"Laura Dern", "Jeff Goldblum", "Richard Attenborough", "Bob Peck"});
     
        Movie movie2 = new Movie("Black Adam",
                ShowingStatus.NOW_SHOWING,
                "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods -- and imprisoned just as quickly -- Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                4.5, MovieType.MOVIE2D, "Jaume Collet-Serra", new String[]{"Dwayne Johnson", "Aldis Hodge", "Noah Centineo", "Sarah Shahi", "Marwan Kenzari", "Quintessa Swindell"});

        Movie movie3 = new Movie("Terrifier",
                ShowingStatus.END_OF_SHOWING,
                "On Halloween night, Tara Heyes finds herself as the obsession of a sadistic murderer known as Art the Clown.",
                2.6, MovieType.MOVIE3D, "Damien Leone", new String[]{"Jenna Kanell", "Samantha Scaffidi", "David Howard Thornton"});

        Movie movie4 = new Movie("Luckiest Girl Alive",
                ShowingStatus.NOW_SHOWING,
                "A woman in New York, who seems to have things under control, is faced with a trauma that makes her life unravel.",
                3.2, MovieType.MOVIE2D, "Mike Barker", new String[]{"Mila Kunis", "Chiara Aurelia", "Finn Wittrock"});

        Movie movie5 = new Movie("Everything Everywhere All at Once",
                ShowingStatus.END_OF_SHOWING,
                "An aging Chinese immigrant is swept up in an insane adventure, in which she alone can save the world by exploring other universes connecting with the lives she could have led.",
                3.2, MovieType.MOVIE2D, "Dan Kwan", new String[]{"Michelle Yeoh", "Stephanie Hsu", "Jamie Lee Curtis"});

        Movie movie6 = new Movie("A Man Called Otto",
                ShowingStatus.PREVIEW,
                "A grumpy widower whose only joy comes from criticizing and judging his exasperated neighbors meets his match when a lively young family moves in next door, leading to an unexpected friendship that will turn his world upside-down.",
                0, MovieType.MOVIE2D, "Marc Forster", new String[]{"Tom Hanks", "Kailey Hyman", "Rachel Keller"});

        Movie movie7 = new Movie("American Psycho",
                ShowingStatus.END_OF_SHOWING,
                "A wealthy New York City investment banking executive, Patrick Bateman, hides his alternate psychopathic ego from his co-workers and friends as he delves deeper into his violent, hedonistic fantasies.",
                3.7, MovieType.BLOCKBUSTER, "Mary Harron", new String[]{"Christian Bale", "Justin Theroux", "Josh Lucas"});

        Movie movie8 = new Movie("Harry Potter and the Philosopher's Stone",
                ShowingStatus.END_OF_SHOWING,
                "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
                3.9, MovieType.MOVIE2D, "Chris Columbus", new String[]{"Daniel Radcliffe", "Rupert Grint", "Richard Harris"});

        Movie movie9 = new Movie("Scream",
                ShowingStatus.NOW_SHOWING,
                "25 years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past.",
                3.1, MovieType.MOVIE3D, "Matt Bettinelli-Olpin", new String[]{"Neve Campbell", "Courteney Cox", "David Arquette"});
     
        Movie movie10 = new Movie("Interstellar",
                ShowingStatus.END_OF_SHOWING,
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
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

    /**
     * Prepare fresh copy of Cineplexes
     */
    public void initCineplex() {
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

        layout = new Layout(5,5);
        layout.setObject(0,0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0,1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0,4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0,3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2,0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2,1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2,4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2,3, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3,0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3,1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(3,3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(3,4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(4,0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4,1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(4,3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(4,4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        cinema = new Cinema("JR2".toCharArray(), layout);
        gvJurong.addCinema(cinema);
        cineplexManager.addCineplex(gvJurong);

        Cineplex gvWoodlands = new Cineplex();
    }
}
