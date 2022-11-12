package boundary.staffUI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MovieManager;
import entity.movie.Movie;
import entity.movie.MovieType;
import entity.movie.ShowingStatus;


public class ManageMovielisting {

	private Scanner sc = new Scanner(System.in);
	private MovieManager moviemanager;

	ManageMovielisting(MovieManager moviemanager)
	{
		this.moviemanager = moviemanager;
	}

	 public  void createmovielisting()
	 {
			int choice;
			String title,director,synopsis;
			MovieType type = null;
			String[] casts;
			ShowingStatus status = null;
			double overallRating = 0;
			System.out.println("Movie's Title: ");
			title = sc.nextLine();
			while(title.isEmpty())
			{
				System.out.println("Enter a valid title!");
				title = sc.nextLine();
				
			}
			System.out.println("Movie's Director:");
			director = sc.nextLine();
			while(director.isEmpty())
			{
				System.out.println("Enter a valid director name!");
				director = sc.nextLine();
			}
			casts = addCast();
			if(casts == null) /*check for invalid casts */
			{
				return;
			}
			System.out.println("Movie's Synopsis:");
            synopsis = sc.nextLine();
			while(synopsis.isEmpty())
			{
				System.out.println("Enter a valid synopsis!");
				synopsis = sc.nextLine();
			}
			try
			{
					System.out.println("Movie's Type: ");
					System.out.println("  (1)MOVIE3D\n " +
									" (2)BLOCKBUSTER\n "+
									" (3)MOVIE2D\n ");
					choice = sc.nextInt();
			
					switch(choice)
					{
						case 1:
						type = MovieType.MOVIE3D;
						break;
						case 2:
						type = MovieType.BLOCKBUSTER;
						break;
						case 3:
						type = MovieType.MOVIE2D;
						break;

					}
					System.out.println("Movie's Status:");
					System.out.println("  (1) COMING SOON\n " +
									" (2) PREVIEW\n "+
									" (3) NOW SHOWING\n " +
									" (4) END OF SHOWING\n");
					choice = sc.nextInt();
					switch(choice)
					{
						case 1:
						status = ShowingStatus.COMING_SOON;
						break;
						case 2:
						status = ShowingStatus.PREVIEW;
						break;
						case 3:
						status = ShowingStatus.NOW_SHOWING;
						break;
						case 4:
						status = ShowingStatus.END_OF_SHOWING;
						break;
					}
			}
			catch (InputMismatchException i)
			{
				System.out.println("Enter a valid input!");
			    return;
			}
			Movie movie = new Movie(title,null,synopsis,overallRating,null,director,casts);
			movie.setType(type);
			movie.setStatus(status);
			moviemanager.addMovie(movie);
			
			System.out.println("New movie list created.");
            

		   
	   
	 }
	 public String[] addCast()
	 {
        int number = 0;
		try
        {
            System.out.print("Number Of Casts: ");
            number = sc.nextInt();
		    sc.nextLine();
        }
        catch(Exception ex)
        {
            System.out.println("Entry is not an integer !");
            return null;
        }
		String []moviecasts = new String[number];
        for (int i = 0; i < moviecasts.length; i++) 
		{
            System.out.print("Movie's Cast " + (i+1) + " : ");
            moviecasts[i] = sc.nextLine();
			if(moviecasts[i].isEmpty())
			{
				System.out.println("Enter a valid cast member name!");
				return null;
			}
            
        }
		return moviecasts;

	 }
	 public  void updatemovielisting()
	 {
		 int option;
		 Movie movie = new Movie(null, null, null, 0, null, null, null);
		 try
		 {
			System.out.println("Which movie do you want to update?");
		    System.out.println("Enter the movie title which you would like to update");
		    String searchtitle = sc.nextLine();
		    movie = moviemanager.searchMovie(searchtitle);
			if(movie == null)
			{
                System.out.println("Movie does not exist...");
                return;
		    }
		 }
		 catch(Exception e)
		 {
			System.out.println("invalid entry!");
			return;
		 }
		
		 do
		 {
			updatemoviemenu();
			try
            {
                System.out.print("Select an option: ");
				option = sc.nextInt();
				sc.nextLine();
            }
            catch(Exception ex)
            {
               System.out.println("Entry is not an integer !");
               return;
            }
			switch(option)
			{
				case 1:
					System.out.println("Enter the updated Title: ");
					String title = sc.nextLine();
					while(title.isEmpty())
					{
						System.out.println("Enter a valid title!");
						title = sc.nextLine();
						
					}
					movie.setTitle(title);
					break;
				case 2:
                     try
					 {
						ShowingStatus status = null;
						System.out.println("Enter the updated Status: ");
						System.out.println("Movie's Status:");
						System.out.println(" (1) COMING SOON\n " +
											"(2) PREVIEW\n "+
											"(3) NOW SHOWING\n " +
											"(4) END OF SHOWING\n");
							int choice = sc.nextInt();
							switch(choice)
							{
							case 1:
								status = ShowingStatus.COMING_SOON;
								break;
							case 2:
								status = ShowingStatus.PREVIEW;
								break;
							case 3:
								status = ShowingStatus.NOW_SHOWING;
								break;
							case 4:
								status = ShowingStatus.END_OF_SHOWING;
								break;
							}
							movie.setStatus(status);
							break;
					 }
					 catch (Exception e)
					 {
						System.out.println("Enter a valid input!");
			            return;
					 }
				case 3:
					System.out.println("Enter the updated Synopsis: ");
					String synopsis = sc.nextLine();
					while(synopsis.isEmpty())
					{
						System.out.println("Enter a valid updated synopsis!");
						synopsis = sc.nextLine();
					}
					movie.setSynopsis(synopsis);
					break;
				case 4:
				    try
					{
						System.out.println("Enter the updated rating: ");
						Float overallrating = sc.nextFloat();
						movie.setOverallRating(overallrating);
						break;
					}
					catch(Exception e)
					{
						System.out.println("Enter a valid updated rating!");
			            return;
					}
				case 5:
				    try
					{
						MovieType type = null;
						System.out.println("Movie's Type:");
						System.out.println("(1)MOVIE3D\n " +
											"(2)BLOCKBUSTER\n "+
											"(3)MOVIE2D\n ");
						int choose = sc.nextInt();
						switch(choose)
						{
							case 1:
							type = MovieType.MOVIE3D;
							break;
							case 2:
							type = MovieType.BLOCKBUSTER;
							break;
							case 3:
							type = MovieType.MOVIE2D;
							break;
						}
						movie.setType(type);
						break;
					}
					catch (Exception e)
					 {
						System.out.println("Enter a valid input!");
			            return;
					 }
				case 6:
					System.out.println("Enter the updated Director: ");
					String director = sc.nextLine();
					while(director.isEmpty())
					{
						System.out.println("Enter a valid director!");
						director = sc.nextLine();
						
					}
					movie.setDirector(director);
					break;
				case 7:
				  String [] casts;
				  casts = addCast();
				  if(casts == null) /*check for invalid casts */
			      {
				     return;
			      }
				  movie.setCasts(casts);
				  break;
				case 8:
				  break;

			}
		 }
		 while(option < 8);

		 
		
		
		 
	 }
	 public static void updatemoviemenu()
	 {

        System.out.println();
        System.out.format(  "====================================\n" +
                "|           Select Option          |\n" +
                "====================================\n" +
                "| (1) Edit Title                   |\n" +
                "| (2) Edit Movie Status               |\n" +
                "| (3) Edit Synopsis                   |\n" +
                "| (4) Edit overallrating               |\n" +
                "| (5) Edit Movie type               |\n" +
                "| (6) Edit Director               |\n" +
                "| (7) Edit Movie casts           |\n" +
                "| (8) Back                         |\n" +
                "====================================\n");
    }
	 public  void removemovielisting()
	 {
		int index = 0,size;
		ArrayList<Movie> movieList = moviemanager.getMovies();
		Hashtable<String,Integer> movietable = new Hashtable<String,Integer>();
		size = movieList.size();
		System.out.println("======= Movies ==========");
		if(size == 0)
		{
			System.out.println( " There are no movies currently...  " );
		}
		else
		{

			for (Movie movie : movieList) 
			{
                System.out.println(movie.getTitle());
				movietable.put(movie.getTitle(),index);
				index++;

		    }
			System.out.println("====================================");
            System.out.println("Which movie do you want to remove?: ");
			System.out.println("Enter full title of movie you want to remove?: ");
			String movietitle= sc.nextLine();
			while(movietitle.isEmpty())
			{
				System.out.println("Enter a valid movie title!");
				movietitle= sc.nextLine();
			}
			int counter = 0;
			for (Movie movie : movieList) 
			{
               if(movie.getTitle().equals(movietitle))
			   {
				  index = movieList.indexOf(movie);
				  counter = 1;
				  break;
			   }


		    }
			if(counter == 1)
			{
				moviemanager.removeMovie(index);
			    System.out.println( " Movie succesfully removed  " );
			}
			else
			{
				System.out.println( " Movie not succesfully removed  " );
			}
		

	    }
	 }

}
