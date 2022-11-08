package boundary.staffUI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import controller.MovieManager;
import entity.movie.Movie;
import entity.movie.MovieType;
import entity.movie.ShowingStatus;


public class ManageMovielisting {

	private Scanner sc = new Scanner(System.in);

	 public  void createmovielisting()
	 {
		    //create a new movie listing by adding a movie from movie manager
			int choice;
			MovieManager moviemanager = new MovieManager();
			String title,director,synopsis;
			float overallRating;
			MovieType type = null;
			String[] casts;
			ShowingStatus status = null;
			System.out.println("Movie's Overall Rating:");
			overallRating = sc.nextFloat();
			System.out.println("Movie's Title: ");
			title = sc.nextLine();
			System.out.println("Movie's Director:");
			director = sc.nextLine();
			casts = addCast();
			System.out.println("Movie's Synopsis:");
            synopsis = sc.nextLine();
			System.out.println("Movie's Type:");
			System.out.println(" (1) MOVIE3D\n " +
			                 "   (2)BLOCKBUSTER\n "+
			                  "  (3)MOVIE2D\n ");
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
			System.out.print("Movie's Status:");
			System.out.println(" (1) COMING SOON\n " +
			                 "   (2) PREVIEW\n "+
			                  "  (3) NOW SHOWING\n " +
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
			Movie movie = new Movie(title,null,synopsis,overallRating,null,director,casts);
			movie.setType(type);
			movie.setStatus(status);
			moviemanager.addMovie(movie);
			
			System.out.println("New movie list created.");
            

		   
	   
	 }
	 public String[] addCast()
	 {

        System.out.print("Number Of Cast: ");
        int number = sc.nextInt();
		sc.nextLine();
		String []moviecasts = new String[number];
        for (int i = 0; i < moviecasts.length; i++) 
		{
            System.out.print("Movie's Cast " + (i+1) + " : ");
            moviecasts[i] = sc.nextLine();
            
        }
		return moviecasts;

	 }
	 public  void updatemovielisting()
	 {
		 MovieManager movies = new MovieManager();
		 int option;
		 Movie movie = new Movie(null, null, null, 0, null, null, null);
		 System.out.println("Which movie do you want to update?");
		 System.out.println("Enter the movie title which you would like to update");
		 String searchtitle = sc.nextLine();
		 movie = movies.searchMovie(searchtitle);
		 do
		 {
			updatemoviemenu();
			System.out.print("Select an option: ");
            option = sc.nextInt();
			switch(option)
			{
				case 1:
				  System.out.println("Enter the updated Title: ");
                  String title = sc.nextLine();
				  movie.setTitle(title);
				  break;
				case 2:
				  ShowingStatus status = null;
				  System.out.println("Enter the updated Status: ");
				  System.out.println("Movie's Status:");
			      System.out.println(" (1) COMING SOON\n " +
			                 "  (2) PREVIEW\n "+
			                  "  (3) NOW SHOWING\n " +
							   " (4) END OF SHOWING\n");
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
				case 3:
				  System.out.println("Enter the updated Synopsis: ");
				  String synopsis = sc.nextLine();
				  movie.setTitle(synopsis);
				  break;
				case 4:
				  System.out.println("Enter the updated rating: ");
				  Float overallrating = sc.nextFloat();
				  movie.setOverallRating(overallrating);
				  break;
				case 5:
				   MovieType type = null;
				   System.out.println("Movie's Type:");
				   System.out.println(" (1) MOVIE3D\n " +
								 "   (2)BLOCKBUSTER\n "+
								  "  (3)MOVIE2D\n ");
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
				case 6:
				  System.out.println("Enter the updated Director: ");
				  String director = sc.nextLine();
				  movie.setDirector(director);
				  break;
				case 7:
				  String [] casts;
				  casts = addCast();
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
		MovieManager removemovie = new MovieManager();
		ArrayList<Movie> movieList = removemovie.getMovies();
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
            System.out.print("Which movie do you want to remove?: ");
			System.out.print("Enter full title of movie you want to remove?: ");
			String movietitle= sc.nextLine();
			for (Movie movie : movieList) 
			{
               if(movie.getTitle().equals(movietitle))
			   {
				  index = movieList.indexOf(movie);
			   }

		    }
			/*Set<Entry<String, Integer> > entrySet = movietable.entrySet();
			int movieindex = 0;
			for (Entry<String,Integer> entry : entrySet)
			{
                 if(entry.getKey().equals(movietitle))
				 {
					movieindex = entry.getValue();
					break;
				 }
			}*/
			removemovie.removeMovie(index);
			System.out.println( " Movie succesfully removed  " );

	    }
	 }

}
