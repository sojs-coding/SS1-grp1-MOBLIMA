package boundary.staffUI;

import controller.StaffAuthenticator;
import controller.BookingManager;
import controller.CineplexManager;
import controller.Initialization;
import controller.MovieManager;
import controller.ShowtimeManager;

import java.util.Scanner;

/**
 * Class for StaffLogin UI to display options for the staff user
 */
public class StaffLoginUI {

	private Scanner sc = new Scanner(System.in);
	/**
	 * Instance variable of Initialization
	 */
    private Initialization initObj = Initialization.getINSTANCE();
    /*
	 * login screen for the cinema staff user
	 */
	public void login()
	{
		System.out.println("==========CINEMA STAFF USER=========");
		System.out.println("Please enter the login details ");
		System.out.println("Enter Staff Username:");
		String username = sc.nextLine();
		System.out.println("Enter Password:");
		String password = sc.nextLine();
		StaffAuthenticator staffAuthenticator = new StaffAuthenticator();
		if (staffAuthenticator.login(username, password)) {
			System.out.println("Login successful!");
			StaffMainMenu();
		} else {
			System.out.println("Login unsuccessful!");
			System.out.println("Please try again");
			login();
		}
	}
	
	public void StaffMainMenu()
	{   
		String choice;
		do
		{
			MovieManager movielist  = initObj.getMovieManager();
			BookingManager booklist =  initObj.getBookingManager();
			CineplexManager cineplexManager = initObj.getCineplexManager();
			ShowtimeManager showtimeManager = initObj.getShowtimeManager();
			
			System.out.println("==== Choose your options =====");
			System.out.println("|(1) Configure system settings |");
			System.out.println("|(2) Manage Movie Listing |");
			System.out.println("|(3) Manage CinemaShowtime |");
			System.out.println("|(4) List top 5 current movies |");
			System.out.println("|(5) exit |");
			System.out.println("=============================");
			
			System.out.println("Enter your choice: ");
			choice = sc.nextLine();
			switch(choice)
			{
			    case "1"://configure system settings
					SystemSettingUI configure = new SystemSettingUI();
					configure.configure();
			    	break;
			    case "2"://manage movie listings
                    ManageMovielisting managemovielisting = new ManageMovielisting(movielist);
			    	String e;
			    	System.out.println("How would you like to manage your movielisting ");
					System.out.println("|(1) Create movie listing |");
					System.out.println("|(2) Update movie listing|");
					System.out.println("|(3) Remove movie listing|");
					System.out.println("=============================");
					e = sc.nextLine();
					switch(e)
					{
					     case "1":
					    	 managemovielisting.createmovielisting();
					    	 break;
					     case "2":
					    	 managemovielisting.updatemovielisting();
					    	 break;
					     case "3":
					    	 managemovielisting.removemovielisting();
					    	 break;
					}
			    	break;
			    case "3"://manage cinemashowtime
			    	ManageShowtime manage = new ManageShowtime(movielist,cineplexManager,showtimeManager);
			    	String c;
			    	System.out.println("How would you like your to manage your movies ");
					System.out.println("|(1) Create cinema showtime |");
					System.out.println("|(2) Update cinema showtime|");
					System.out.println("|(3) Remove cinema showtime|");
					System.out.println("=============================");
					c = sc.nextLine();
					switch(c)
					{
					     case "1":
					    	 manage.createCinemaShowtime();
					    	 break;
					     case "2":
					    	 manage.updateCinemaShowtime();
					    	 break;
					     case "3":
					    	 manage.removeCinemaShowtime();
					    	 break;
					}
			    	break;
			    case "4":	//list top 5 current movies
			    	String option;
			    	ListTop5 list = new ListTop5(movielist,booklist);
					System.out.println("How would you like your top 5 movies to be sorted by ");
					System.out.println("|(1) Top 5 based on user ratings |");
					System.out.println("|(2) Top 5 based on overall movie sales |");
					System.out.println("=============================");
					option = sc.nextLine();
					switch(option)
					{
					     case "1":
					    	 list.printTop5MoviesByRatings();
					    	 break;
					     case "2":
					    	 list.printTop5MoviesBySales();
					    	 break;
					}
			    	break;
				case "5":
					break;
			}
		}
		while(!choice.equals("5"));
		System.out.println("Goodbye! ");
		
	}
	
	
	
	
		

}
