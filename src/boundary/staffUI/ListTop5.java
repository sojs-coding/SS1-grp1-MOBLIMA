package boundary.staffUI;
import java.util.*;
import java.util.Map.Entry;
import controller.MovieManager;
import entity.movie.Movie;
import controller.BookingManager;
import entity.booking.Booking;


public class ListTop5 {
	
	 private MovieManager moviemanager;
     private BookingManager bookingmanager;

    ListTop5(MovieManager moviemanager,BookingManager bookingmanager)
	{
		this.moviemanager = moviemanager;
        this.bookingmanager = bookingmanager;
	}


	 public void printTop5MoviesByRatings() 
	 {
		 ArrayList<Movie> movies = moviemanager.getMovies();
		 LinkedHashMap<String, Float> ratings = new LinkedHashMap<String, Float>();
		 for(Movie movie : movies) //loop through the array of movies
		 {
			if(ratings.containsKey(movie.getTitle()))//check for duplicate movies
			{
				continue;
			}
			else
			{
				ratings.put(movie.getTitle(), (float) movie.getOverallRating());
			}
         }
		 Map<String, Float> sortedratings = sortbyratings(ratings);

        // convert to ArrayList of key set
       List<String> sortedratingsreverse
       = new ArrayList<String>(sortedratings.keySet());

        // reverse order of keys to print in descending order
        Collections.reverse(sortedratingsreverse);
         
		 
		System.out.println("--------------------------------------------------------------------");
        System.out.println("                  Top 5 Movies (By Ratings)                    ");
        System.out.println("--------------------------------------------------------------------");
		 
		 for ( String key: sortedratingsreverse) 
		 {
			System.out.println("Movie title :" + key +", Rating = " + sortedratings.get(key));
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

        ArrayList<Booking> bookings = bookingmanager.getBookings();
        int size = bookings.size();
        int i = 0;
        HashMap<String,Integer> movieCount = new HashMap<String,Integer>();
		 while (i < size){
            String movieTitle = bookings.get(i).getCentral().getMovie().getTitle();
            if(((HashMap<String, Integer>) movieCount).containsKey(movieTitle)){
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
	 private static HashMap<String, Float> sortbyratings (LinkedHashMap <String, Float> temp )
	 {
		  //get all the entries from the hashtable and put it in a List
          List<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(temp.entrySet());
          
		  //sort the entries based on the value by custom Comparator
          Collections.sort(list, new Comparator<Map.Entry<String, Float>>()
		  {
 
	         public int compare(Map.Entry<String, Float> entry1, Map.Entry<String, Float> entry2) 
		     {
		         return entry1.getValue().compareTo( entry2.getValue() );
	         }
          });

		  LinkedHashMap <String, Float> mapSortedByValues = new LinkedHashMap<String, Float>();

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
