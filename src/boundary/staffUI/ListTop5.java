package boundary.staffUI;
import java.util.*;
import java.util.Map.Entry;
import controller.MovieManager;
import java.util.ArrayList;
import entity.movie.Movie;
import java.util.HashMap;
import controller.BookingManager;
import entity.booking.Booking;


public class ListTop5 {
	
	
	 public void printTop5MoviesByRatings() 
	 {
		 MovieManager movielist= new MovieManager();
		 ArrayList<Movie> movies = movielist.getMovies();
		 HashMap<String, Float> ratings = new HashMap<String, Float>();
		 for(Movie movie : movies) //loop through the array of movies
		 {
			if(ratings.containsKey(movie.getTitle()))//check for duplicate movies
			{
				continue;
			}
			else
			{
				ratings.put(movie.getTitle(), movie.getOverallRating());
			}
         }
		 Map<String, Float> sortedratings = sortbyratings(ratings);
		 
		System.out.println("--------------------------------------------------------------------");
        System.out.println("                  Top 5 Movies (By Ratings)                    ");
        System.out.println("--------------------------------------------------------------------");
		 
		 for (Map.Entry<String, Float> entry : sortedratings.entrySet()) 
		 {
			System.out.println("Movie title :" + entry.getKey() +", Rating = " + entry.getValue());
		 }
		System.out.println("--------------------------------------------------------------------");

		 
		
	 }
	 public String getMaxCount(Map<String,Integer> movieCount){
        int max = 0;
        String maxString = "";

        for(Map.Entry<String,Integer> entry : movieCount.entrySet()){
            String key = entry.getKey();
            int val = entry.getValue();

            if (val > max){
                maxString = key;
                max = val;
            }
        }

        return maxString;

    }
	 public  void printTop5MoviesBySales()
	 {	
		BookingManager bookingManager = new BookingManager();
        ArrayList<Booking> bookings = bookingManager.getBookings();
        int size = bookings.size();
        int i = 0;
        Map<String,Integer> movieCount = new HashMap<String,Integer>();
		 while (i < size){
            String movieTitle = bookings.get(i).getCentral().getMovie().getTitle();
            if(((Hashtable<String, Integer>) movieCount).containsKey(movieTitle)){
                movieCount.put(movieTitle,movieCount.get(movieTitle)+1);
            }
            else{
                movieCount.put(movieTitle,1);
            }
            i++;
        } 
		int count = 5;
        System.out.println("--------------------------------------------------------------------");
        System.out.println("                  Top 5 Movies (By Ticket Sales)                    ");
        System.out.println("--------------------------------------------------------------------");
        while (count > 0){
            if(!movieCount.isEmpty()){
                String topMovie = getMaxCount(movieCount);
                System.out.printf("(1): %s , Ticket Sales: %d\n",topMovie,movieCount.get(topMovie));
                movieCount.remove(topMovie);
            }
            else{
                System.out.println("There are less than 5 movies premiered...");
                break;
            }
        }
        System.out.println("--------------------------------------------------------------------");

		 
	 }
	 private static HashMap<String, Float> sortbyratings (HashMap <String, Float> temp )
	 {
		  //get all the entries from the hashtable and put it in a List
          List<Map.Entry<String, Float>> list = new ArrayList<Entry<String, Float>>(temp.entrySet());

		  //sort the entries based on the value by custom Comparator
          Collections.sort(list, new Comparator<Map.Entry<String, Float>>()
		  {
 
	         public int compare(Entry<String, Float> entry1, Entry<String, Float> entry2) 
		     {
		         return entry1.getValue().compareTo( entry2.getValue() );
	         }
          });

		  HashMap <String, Float> mapSortedByValues = new LinkedHashMap<String, Float>();

		  //put all sorted entries in LinkedHashMap
          for( Map.Entry<String, Float> entry : list  )
		  {
              mapSortedByValues.put(entry.getKey(), entry.getValue());
          }

		  for (Map.Entry<String,Float> entry : list) 
		  {
			if(mapSortedByValues.size() > 5)
			{
				mapSortedByValues.remove(entry.getKey());
			}
 
         }

		  return mapSortedByValues;


	 }
	 


}
