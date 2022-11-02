import controller.BookingManager;
import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import entity.cinema.*;
import entity.movie.Movie;
import entity.movie.MovieType;
import entity.movie.ShowingStatus;

import java.io.*;

public class Initialization implements Serializable{
    private static Initialization INSTANCE;
    CineplexManager cineplexManager;
    BookingManager bookingManager;
    MovieManager movieManager;
    ShowtimeManager showtimeManager;
    Initialization() {
        if (Initialization.INSTANCE != null) {
            throw new ExceptionInInitializerError();
        }
        cineplexManager = new CineplexManager();
        bookingManager = new BookingManager();
        movieManager = new MovieManager();
        showtimeManager = new ShowtimeManager();
    }
    public static Initialization getINSTANCE() {
        if (Initialization.INSTANCE == null)
        {
            Initialization.INSTANCE = new Initialization();
        }
        return Initialization.INSTANCE;
    }
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

    public void saveProgram() {
        try {
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

    public void initMovie() {
        Movie movie = new Movie("Jurassic Park",
                ShowingStatus.NOW_SHOWING,
                "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA. Before opening day, he invites a team of experts and his two eager grandchildren to experience the park and help calm anxious investors. However, the park is anything but amusing as the security systems go off-line and the dinosaurs escape. ",
                0, MovieType.BLOCKBUSTER, "Me", new String[]{"Laura Dern", "Jeff Goldblum", "Richard Attenborough", "Bob Peck"});
        movieManager.addMovie(movie);
    }

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
        cineplexManager.addCineplex(gvJurong);
    }
}
