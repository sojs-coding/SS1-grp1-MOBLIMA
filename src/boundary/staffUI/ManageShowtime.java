package boundary.staffUI;
import controller.ShowtimeManager;
import java.util.Scanner;
import entity.cinema.Cineplex;
import entity.cinema.Cinema;
import entity.cinema.Layout;
import entity.cinema.Showtime;
import java.time.LocalDateTime;
import entity.movie.Movie;
import controller.CineplexManager;
import controller.MovieManager;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;  
import java.util.*;





public class ManageShowtime {
	
	Scanner sc = new Scanner(System.in);

	private MovieManager movieManager;
	private CineplexManager cineplexManager;
	private ShowtimeManager showtimeManager;

	public ManageShowtime(MovieManager moviemanager,CineplexManager cineplexManager,ShowtimeManager showtimeManager)
	{
        this.movieManager = moviemanager;
		this.cineplexManager = cineplexManager;
		this.showtimeManager = showtimeManager;
		
	}

	public  void createCinemaShowtime()
	{
        
		System.out.println("How many showtimes do you want to add?");
        int num = sc.nextInt();
		ArrayList<Movie> movielist = movieManager.getMovies();
		Movie movie = new Movie(null, null, null, 0, null, null, null);
		Cineplex cineplex = new Cineplex(null);
        LocalDateTime localDateTime;
		DateTimeFormatter DATE_TIME_FORMATTER;
		for (int i = 0; i < num; i++)
		{
			printMovies(movielist);
			System.out.println(" (1) What is the title of the movie you would like to add to the showtime?");
			sc.nextLine();
			String title = sc.nextLine();
			movie = movieManager.searchMovie(title);
			if(movie == null)
			{
                System.out.println("Movie does not exist...");
                return;
            }
			
			
            try 
			{
				printCineplex(cineplexManager);
				System.out.println("(2) Which cineplex would you like to add showtime to?");
				System.out.println("Enter the title of the cineplex...");
				String cineplextitle = sc.nextLine();
				cineplex = cineplexManager.searchCompany(cineplextitle);
				if(cineplex == null)
				{
					System.out.println("Cineplex does not exist...");
					return;
				}
            }
            catch (Exception e) 
			{
					System.out.println("Invalid entry...");
					sc.close();
					return;
            }
            printCinemas(cineplex);
			System.out.println(" (3) Enter the  cinema code to be added to the showtime");
			System.out.println(" Enter in format : Woodlands(WL1) , Jurong(JR1) , Tampines(TM1)");
			String cinemacode = sc.nextLine();
			//need copy layout from cineplex
			Cinema cinema = cineplex.findCinema(cinemacode.toCharArray());
			
			System.out.println(" (4) Enter the date and time for this cinema showtime");
			System.out.println(" yyyy-MM-dd HH:mm ");
			String dateTime = sc.nextLine();
			DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			localDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
            
			Showtime showtime = new Showtime(localDateTime,cinema,movie);
			showtimeManager.addShowtime(showtime);

			System.out.println("Showtime added");

		}
        
		System.out.println("Showtime successfully created");
	}
	
	public void updateCinemaShowtime()
	{
		ArrayList<Movie> movielist = movieManager.getMovies();
		Movie movie = new Movie(null, null, null, 0, null, null, null);
		Cineplex cineplex = new Cineplex(null);
        LocalDateTime localDateTime;
		DateTimeFormatter DATE_TIME_FORMATTER;
		System.out.println("Which showtime would you like to update?");
		printShowTimes(showtimeManager);
		System.out.println("Enter the movie title for which you would like to update showtime for :");
		String title = sc.nextLine();
		movie = movieManager.searchMovie(title);
		if(movie == null)
		{
            System.out.println("Movie does not exist...");
            return;
        }
		try 
		{
				System.out.println("(2) Which cineplex would you like to update showtime to?");
				System.out.println("Enter the title of the cineplex...");
				String cineplextitle = sc.nextLine();
				cineplex = cineplexManager.searchCompany(cineplextitle);
				if(cineplex == null)
				{
					System.out.println("Cineplex does not exist...");
					return;
				}
        }
        catch (Exception e) 
		{
					System.out.println("Invalid entry...");
					sc.close();
					return;
         }
		 System.out.println(" (3) Enter the  cinema code you want to update");
		 System.out.println(" Enter in format : Woodlands(WL1) , Jurong(JR1) , Tampines(TM1)");
		 String cinemacode = sc.nextLine();
		Cinema cinema = cineplex.findCinema(cinemacode.toCharArray());


        int index = 0;
		for(Showtime showtime : showtimeManager.getShowtimes())
		{
            if (showtime.getMovie() == movie && showtime.getCinema() == cinema)
			{
               break;
			}
			index++;
		}
		showtimeManager.removeShowtime(index);
		System.out.println("Enter the updated movie title :");
		String newtitle = sc.nextLine();
		Movie newmovie = movieManager.searchMovie(newtitle);
		if(newmovie == null)
		{
            System.out.println("Movie does not exist...");
            return;
        }
		System.out.println("  Enter the  updated cinema code");
		System.out.println(" Enter in format : Woodlands(WL1) , Jurong(JR1) , Tampines(TM1)");
		String newcinemacode = sc.nextLine();
		Cinema newcinema = cineplex.findCinema(newcinemacode.toCharArray());
		System.out.println(" Enter the updated datetime");
		System.out.println(" yyyy-MM-dd HH:mm ");
		String newdateTime = sc.nextLine();
		DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime newlocalDateTime = LocalDateTime.parse(newdateTime, DATE_TIME_FORMATTER);
		Showtime targetshowtime = new Showtime(newlocalDateTime, newcinema, newmovie);
		showtimeManager.addShowtime(targetshowtime);

		
		
    
		


		
		System.out.println("Showtime successfully updated");
	}
	
	public  void removeCinemaShowtime()
	{
		Cineplex cineplex = new Cineplex(null);
        Movie movie = new Movie(null, null, null, 0, null, null, null);

		System.out.println("Which showtime would you like to remove?");
		printShowTimes(showtimeManager);
		System.out.println("Enter the movie title for which you would like to remove showtime for :");
		String title = sc.nextLine();
		movie = movieManager.searchMovie(title);
		if(movie == null)
		{
            System.out.println("Movie does not exist...");
            return;
        }
		try 
		{
				System.out.println("(2) Which cineplex would you like to update showtime to?");
				System.out.println("Enter the title of the cineplex...");
				String cineplextitle = sc.nextLine();
				cineplex = cineplexManager.searchCompany(cineplextitle);
				if(cineplex == null)
				{
					System.out.println("Cineplex does not exist...");
					return;
				}
        }
        catch (Exception e) 
		{
					System.out.println("Invalid entry...");
					sc.close();
					return;
         }
		 System.out.println(" (3) Enter the  cinema code you want to remove");
		 System.out.println(" Enter in format : Woodlands(WL1) , Jurong(JR1) , Tampines(TM1)");
		 String cinemacode = sc.nextLine();
		 Cinema cinema = cineplex.findCinema(cinemacode.toCharArray());


		int index = 0;
		for(Showtime showtime : showtimeManager.getShowtimes())
		{
            if (showtime.getMovie().getTitle() == movie.getTitle() && showtime.getCinema() == cinema)
			{
                break;
			}
			index++;
		}
		showtimeManager.removeShowtime(index);

		
		System.out.println("Showtime successfully removed");

	}

	public static void printMovies(ArrayList<Movie> movielist)
	{
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Movie List                             ");
        System.out.println("--------------------------------------------------------------------");
        for (Movie movie : movielist)
		{
            System.out.println(movie.getTitle());
        }
        System.out.println("--------------------------------------------------------------------");
    }

	public static void printCinemas(Cineplex cineplex)
	{
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

	public static void printShowTimes(ShowtimeManager showtimeManager)
	{

        ArrayList<Showtime> showTimes = showtimeManager.getShowtimes();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                           Showtimes List                             ");
        System.out.println("--------------------------------------------------------------------");
        for(Showtime show : showTimes)
		{
			System.out.println("Movie :" + show.getMovie().getTitle() + " Cinema : " + new String(show.getCinema().getCode()) + " Date : " + dtf.format(show.getDateTime()) );
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

	

}

