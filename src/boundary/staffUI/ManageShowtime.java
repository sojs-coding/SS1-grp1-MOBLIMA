package boundary.staffUI;
import controller.ShowtimeManager;
import java.util.Scanner;
import entity.cinema.Cineplex;
import entity.cinema.Cinema;
import entity.cinema.Layout;
import entity.cinema.Showtime;
import java.time.LocalDateTime;
import entity.movie.Movie;
import controller.MovieManager;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;  





public class ManageShowtime {
	
	Scanner sc = new Scanner(System.in);

	public  void createCinemaShowtime()
	{
		ShowtimeManager showtimeManager = new ShowtimeManager();
		Cineplex cineplex = new Cineplex(null);
		MovieManager movieManager = new MovieManager();
        
		System.out.println("How many showtimes do you want to add?");
        int num = sc.nextInt();
		ArrayList<Movie> movielist = movieManager.getMovies();
		Movie movie = new Movie(null, null, null, 0, null, null, null);
		Cinema cinema = new Cinema(null, null);
        LocalDateTime localDateTime;
		DateTimeFormatter DATE_TIME_FORMATTER;
		for (int i = 0; i < num; i++)
		{
			printMovies();
			System.out.println(" (1) What is the title of the movie you would like to add?");
			String title = sc.nextLine();
			movie = movieManager.searchMovie(title);
			printCinemas();
			System.out.println("(2) Enter your choice of cinema: ");
            System.out.println("Enter the three character code");
            char[] code = {};
            for(int j = 0; j < 3; j++){
                code[j] = sc.next().charAt(0);
            }
            cinema = cineplex.findCinema(code);
			System.out.println(" (3) Enter the date and time for this cinema showtime");
			System.out.println(" yyyy-MM-dd HH:mm ");
			String dateTime = sc.nextLine();
			DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			localDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);

			//add showtime 
			Showtime showtime = new Showtime(localDateTime,cinema,movie);
			showtimeManager.addShowtime(showtime);
			

		}
        

		System.out.println("Showtime successfully created");
	}
	
	public void updateCinemaShowtime()
	{
		//update showtime based on cinemashowtime manager
		//update any attributes in cinemashowtime
		System.out.println("Showtime successfully updated");
	}
	
	public  void removeCinemaShowtime()
	{
		printShowTimes();
		ShowtimeManager showtimeManager = new ShowtimeManager();
		Cineplex cineplex = new Cineplex(null);
		MovieManager movieManager = new MovieManager();
	    ArrayList<Movie> movielist = movieManager.getMovies();
        Movie movie = new Movie(null, null, null, 0, null, null, null);
		
		System.out.println("Showtime successfully removed");

	}
	
	public static void printMovies()
	{
        MovieManager MovieManager = new MovieManager();
        ArrayList<Movie> movies = MovieManager.getMovies();
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

	public static void printCinemas(){
        Cineplex cineplex = new Cineplex(null);
        ArrayList<Cinema> cinemas = cineplex.getCinemas();
        int i = 0;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Cinemas List                             ");
        System.out.println("--------------------------------------------------------------------");
        while (i < cinemas.size()){
            System.out.println(cinemas.get(i).getCode());
            i++;
        }
        System.out.println("--------------------------------------------------------------------");
    }

	public static void printShowTimes(){
        ShowtimeManager showTime = new ShowtimeManager();
        ArrayList<Showtime> showTimes = showTime.getShowtimes();
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Showtimes List                             ");
        System.out.println("--------------------------------------------------------------------");
        for(Showtime show : showTimes)
		{
            System.out.print(show.getCinema());
			System.out.print(show.getMovie());
			System.out.print(show.getDateTime());
			System.out.println();
		}
        System.out.println("--------------------------------------------------------------------");
    }

}
