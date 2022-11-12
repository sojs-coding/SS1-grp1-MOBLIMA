package boundary.staffUI;
import controller.ShowtimeManager;
import java.util.Scanner;
import entity.cinema.Cineplex;
import entity.cinema.Cinema;
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
import java.time.format.DateTimeParseException;  






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
		ArrayList<Movie> movielist = movieManager.getMovies();
		Movie movie = new Movie(null, null, null, 0, null, null, null);
		Cineplex cineplex = new Cineplex(null);
        LocalDateTime localDateTime = null;
		DateTimeFormatter DATE_TIME_FORMATTER;
        String num = null;
		int number = 0;

		try{
			System.out.println("How many showtimes do you want to add?");
			num = sc.nextLine();
			number = Integer.parseInt( num );
		}
		catch(Exception e)
		{
			System.out.println("Invalid input!...");
		}

		for (int i = 0; i < number; i++)
		{
			printMovies(movielist);
			System.out.println(" (1) What is the title of the movie you would like to add to the showtime?");
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
			for(Showtime showtime : showtimeManager.getShowtimes())
			{
                 if( new String(showtime.getCinema().getCode()).equals( cinemacode) )
				 {
					System.out.println("Cinema code already exists in showtime!");
					return;
				 }
			}
			int counter = 0;
			for(Cineplex cineplexer : cineplexManager.getCineplexes())
			{
				for(Cinema cinematest : cineplexer.getCinemas())
				{
                     if( new String (cinematest.getCode()).equals(cinemacode))
					 {
                        counter = 1;
						break;
					 }
				}
			}
			if(counter == 0)
			{
				System.out.println("Cinema code does not exist at all!");
				return;
			}
			//need copy layout from cineplex
			Cinema cinema = cineplex.findCinema(cinemacode.toCharArray());
			try
			{
				System.out.println(" (4) Enter the date and time for this cinema showtime");
				System.out.println(" yyyy-MM-dd HH:mm ");
				String dateTime = sc.nextLine();
				DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				localDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
			}
		   catch (Exception e) 
		   {
			 System.out.println("Date is invalid !");
			 return;
		   }
			Showtime showtime = new Showtime(localDateTime,cinema,movie);
			showtimeManager.addShowtime(showtime);

			System.out.println("Showtime added");

		}
        
		System.out.println("Showtime successfully created");
	}
	
	public void updateCinemaShowtime()
	{
		Movie movie = new Movie(null, null, null, 0, null, null, null);
		DateTimeFormatter DATE_TIME_FORMATTER;
		System.out.println("Which showtime would you like to update?");
		printShowTimes(showtimeManager);
		System.out.println(" Enter the movie title for which you would like to update showtime for :");
		String title = sc.nextLine();
		while(title.isEmpty())
		{
			System.out.println("Enter a valid title!");
			title = sc.nextLine();
						
		}
		movie = movieManager.searchMovie(title);
		if(movie == null)
		{
            System.out.println("Movie does not exist...");
            return;
        }
		 System.out.println(" Enter the  cinema code you want to update");
		 System.out.println(" Enter in format : Woodlands(WL1) , Jurong(JR1) , Tampines(TM1)");
		 String cinemacode = sc.nextLine();
		 while(cinemacode.isEmpty())
		{
			System.out.println("Enter a valid code!");
			cinemacode= sc.nextLine();
						
		}
		Cinema targetcinema = null;
		Cineplex newcineplex = null;
		ArrayList<Cineplex> cineplexlist = cineplexManager.getCineplexes();
		for(Cineplex cineplex : cineplexlist)
		{
			for(Cinema cinema : cineplex.getCinemas())
			{
				if(new String(cinemacode).equals(new String(cinema.getCode())))
				{
					targetcinema = cineplex.findCinema(cinemacode.toCharArray());
					newcineplex = cineplex;
				}
			}
		}
		 if(targetcinema == null)
		 {
			System.out.println("Cinema code cant be found in the cineplex");
			return;
		 }


        int index = 0;
		Showtime targetshowtime = null;
		for(Showtime showtime : showtimeManager.getShowtimes())
		{
            if (showtime.getMovie() == movie && showtime.getCinema() == targetcinema)
			{
				targetshowtime = showtimeManager.getShowtime(index);
			}
			index++;
		}
		if(targetshowtime == null)
		{
			System.out.println("Showtime does not exist...");
			return;
		}
		System.out.println("Enter the updated movie title :");
		String newtitle = sc.nextLine();
		Movie newmovie = movieManager.searchMovie(newtitle);
		if(newmovie == null)
		{
            System.out.println("Movie does not exist...");
            return;
        }
		System.out.println(" Enter the  updated cinema code");
		System.out.println(" Enter in format : Woodlands(WL1) , Jurong(JR1) , Tampines(TM1)");
		String newcinemacode = sc.nextLine();
		if(newcinemacode.isEmpty())
		{
			System.out.println("Enter a valid code !");
			newcinemacode = sc.nextLine();
		}
		for(Showtime showtime : showtimeManager.getShowtimes()) //check if cinema code already exists
		{
			     if(newcinemacode.equals(cinemacode)) //means no update of the old cinema code
				 {
					break;
				 }
                 else if( new String(showtime.getCinema().getCode()).equals( newcinemacode) )
				 {
					System.out.println("Cinema code already exists in showtime!");
					return;
				 }
		}
		int counter = 0;
		for(Cineplex cineplexer : cineplexManager.getCineplexes())
		{
				for(Cinema cinematest : cineplexer.getCinemas())
				{
                     if( new String (cinematest.getCode()).equals(newcinemacode))
					 {
                        counter = 1;
						break;
					 }
				}
		}
		if(counter == 0)
		{
				System.out.println("Cinema code does not exist at all!");
				return;
		}
		Cinema newcinema = newcineplex.findCinema(newcinemacode.toCharArray());
		if(newcinema == null)
		{
			System.out.println("This cinema code does not exist in this cineplex!");
			return;
		}
		String newdateTime = null;
		LocalDateTime newlocalDateTime;
		try
		{
				System.out.println(" (4) Enter the date and time for this cinema showtime");
				System.out.println(" yyyy-MM-dd HH:mm ");
				newdateTime = sc.nextLine();
				DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				newlocalDateTime = LocalDateTime.parse(newdateTime, DATE_TIME_FORMATTER);
				
		}
		catch (Exception e) 
		{
			 System.out.println("Date is invalid !");
			 return;
		}
		
		targetshowtime.setCinema(newcinema);
		targetshowtime.setDateTime(newlocalDateTime);
		targetshowtime.setMovie(newmovie);

		
		System.out.println("Showtime successfully updated");
	}
	
	public  void removeCinemaShowtime()
	{
        Movie movie = new Movie(null, null, null, 0, null, null, null);

		System.out.println("Which showtime would you like to remove?");
		printShowTimes(showtimeManager);
		System.out.println("Enter the movie title for which you would like to remove showtime for :");
		String title = sc.nextLine();
		while(title.isEmpty())
		{
			System.out.println("Enter a valid movie title!");
			title = sc.nextLine();
		}
		movie = movieManager.searchMovie(title);
		if(movie == null)
		{
            System.out.println("Movie does not exist...");
            return;
        }
		int check = 0;
		//Check if any movie in database is wrongly keyed in to be removed
		for(Showtime showtime : showtimeManager.getShowtimes())
		{
                 if( movie.getTitle().equals(showtime.getMovie().getTitle()) )
				 {
					check = 1;
					break;
				 }
		}
		if(check == 0)
		{
			System.out.println("Movie is not listed on showtime!");
			return;
		}
		System.out.println(" Enter the  cinema code you want to remove");
		System.out.println(" Enter in format : Woodlands(WL1) , Jurong(JR1) , Tampines(TM1)");
		String cinemacode = sc.nextLine();
		while(cinemacode.isEmpty())
	    {
		   System.out.println("Enter a non-empty code!");
		   cinemacode= sc.nextLine();
					   
	    }
		Cinema targetcinema = null;
		//check if the cinema code exists in the current showtime and also get the target cinema
		for(Showtime showtime : showtimeManager.getShowtimes())
		{
            if( new String (showtime.getCinema().getCode()).equals(cinemacode))
			{
				targetcinema = showtime.getCinema();
				break;
			}
		}
		if(targetcinema == null)
		{
			System.out.println("Enter a valid code that is on showtime!");
			return;
		}
		int index = 0;
		//find index and check thoroughly if targetcinema is really the one to be removed
		for(Showtime showtime : showtimeManager.getShowtimes())
		{
            if (showtime.getMovie().getTitle() == movie.getTitle() && showtime.getCinema() == targetcinema)
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