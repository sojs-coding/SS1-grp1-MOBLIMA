package boundary.staffUI;

import controller.StaffAuthenticator;

import java.util.Scanner;

public class StaffLoginUI {
	private Scanner sc = new Scanner(System.in);

	public void login()
	{
		System.out.println("CINEMA STAFF USER:");
		System.out.println("Please enter the login details ");
		System.out.println("Enter Staff Username:");
		//code to check if username exist in database
		String username = sc.nextLine();
		System.out.println("Enter Password:");
		//code to check if password exist in database
		String password = sc.nextLine();
		//if check successful print below statement
		StaffAuthenticator staffAuthenticator = new StaffAuthenticator();
		if (staffAuthenticator.login(username, password)) {
			System.out.println("Login successful!");
			StaffMainMenu();
		} else {
			// ???
		}
	}
	
	public void StaffMainMenu()
	{   
		int choice;
		do
		{
			System.out.println("==== Choose your options =====");
			System.out.println("|(1) Configure system settings |");
			System.out.println("|(2) Manage Movie Listing |");
			System.out.println("|(3) Manage CinemaShowtime |");
			System.out.println("|(4) List top 5 current movies |");
			System.out.println("|(5) exit |");
			System.out.println("=============================");
			
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice)
			{
			    case 1://configure system settings
			    	SystemSettingUI configure = new SystemSettingUI();
			    	System.out.println("How would you like to configure System settings ");
			    	System.out.println("|(1) Configure holidays |");
			    	System.out.println("|(2) Configure ticket prices |");
			    	System.out.println("=============================");
			    	int c2;
			    	c2 = sc.nextInt();
					switch(c2)
					{
					     case 1:
					    	 configure.configureHoliday();
					    	 break;
					     case 2:
					         configure.configureTicketPrice();
					    	 break;
					}
			    	break;
			    case 2://manage movie listings
			    	ManageMovielisting movielist = new ManageMovielisting();
			    	int e;
			    	System.out.println("How would you like to manage your movielisting ");
					System.out.println("|(1) Create movie listing |");
					System.out.println("|(2) Update movie listing|");
					System.out.println("|(3) Remove movie listing|");
					System.out.println("=============================");
					e = sc.nextInt();
					switch(e)
					{
					     case 1:
					    	 movielist.createmovielisting();
					    	 break;
					     case 2:
					    	 movielist.updatemovielisting();
					    	 break;
					     case 3:
					    	 movielist.removemovielisting();
					    	 break;
					}
			    	break;
			    case 3://manage cinemashowtime
			    	ManageShowtime manage = new ManageShowtime();
			    	int c;
			    	System.out.println("How would you like your to manage your movies ");
					System.out.println("|(1) Create cinema showtime |");
					System.out.println("|(2) Update cinema showtime|");
					System.out.println("|(3) Remove cinema showtime|");
					System.out.println("=============================");
					c = sc.nextInt();
					switch(c)
					{
					     case 1:
					    	 manage.createCinemaShowtime();
					    	 break;
					     case 2:
					    	 manage.updateCinemaShowtime();
					    	 break;
					     case 3:
					    	 manage.removeCinemaShowtime();
					    	 break;
					}
			    	break;
			    case 4:	//list top 5 current movies
			    	int option;
			    	ListTop5 list = new ListTop5();
					System.out.println("How would you like your top 5 movies to be sorted by ");
					System.out.println("|(1) Top 5 based on user ratings |");
					System.out.println("|(2) Top 5 based on overall movie sales |");
					System.out.println("=============================");
					option = sc.nextInt();
					switch(option)
					{
					     case 1:
					    	 list.printTop5MoviesByRatings();
					    	 break;
					     case 2:
					    	 list.printTop5MoviesBySales();
					    	 break;
					}
			    	break;
			}
			
		}
		while(choice < 5);
		System.out.println("Goodbye! ");
		
	}
	
	
	
	
		

}
