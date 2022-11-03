import controller.BookingManager;
import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import entity.cinema.*;
import entity.movie.Movie;
import entity.movie.MovieType;
import entity.movie.ShowingStatus;
import java.io.*;

/**
Represents the Initialization of the application
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
    CineplexManager cineplexManager;
    /**
     * A manager to manage all the bookings
     */
    BookingManager bookingManager;
    /**
     * A manager to manage all the movies
     */
    MovieManager movieManager;
    /**
     * A manager to manage all the showtimes
     */
    ShowtimeManager showtimeManager;

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
     * Instantiate a new Initialization if null
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
     * @return INSTANCE of Initialization
     */
    public static Initialization initProgram() {
        File f = new File("./cinemadata/init.ser");
        if (f.exists() && !f.isDirectory()) {
            // do something
            try {
                FileInputStream fileIn = new FileInputStream("./cinemadata/init.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Initialization.INSTANCE = (Initialization) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return null;
            } catch (ClassNotFoundException c) {
                System.out.println("Cineplex Manager class not found");
                c.printStackTrace();
                return null;
            }
            System.out.println("Loaded");
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
        Movie movie = new Movie("Jurassic Park",
                ShowingStatus.NOW_SHOWING,
                "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA. Before opening day, he invites a team of experts and his two eager grandchildren to experience the park and help calm anxious investors. However, the park is anything but amusing as the security systems go off-line and the dinosaurs escape. ",
                0, MovieType.BLOCKBUSTER, "Me", new String[]{"Laura Dern", "Jeff Goldblum", "Richard Attenborough", "Bob Peck"});
        movieManager.addMovie(movie);
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
