package boundary.movieGoerUI;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieUI {

	Scanner sc = new Scanner(System.in);
	private MovieManager movieMgr;
	
	movieUI(MovieManager movieMgr){
		this.movieMgr = movieMgr;
	}
	
	public void printAllMovies() {
		for(int i = 0; i < movieMgr.getSize(); i++) {
			Movie movie = movieMgr.getMovie(i);
			String title = movie.getTitle();
			System.out.printf("%d) %s",i+1, title);	
			System.out.println();
		}
	}
	
	public void searchMovie() {
		String movieString;
		System.out.println("Please enter the Title of the movie: ");
		movieString = sc.next();
		Movie targetMovie = movieMgr.searchMovie(movieString);
	}
	
	public void viewMovieDetails(Movie movie) {
		
		String[] casts = movie.getCasts();
		ArrayList<Review> reviews = movie.getReview();
		System.out.println("Title: " + movie.getTitle());
		System.out.print("Casts: ");
		for(int i = 0; i < casts.length; i++) {
			System.out.print(casts[i] + ", ");
		}
		System.out.println();
		System.out.println("Director: " + movie.getTitle());
		System.out.println("Synopsis: " + movie.getSynopsis());
		System.out.println("Movie Type: ");
		System.out.print("Reviews: ");
		for(int i = 0; i < reviews.size(); i++) {
			Review review = reviews.get(i);
			System.out.printf(review.getReview() + " Rating: %d/5",review.getRating());
			System.out.println();
		}
		System.out.println("Status: " + movie.getStatus());
	}
	
	public void leaveReview() {
		
		Review review;
		String movieString, reviewStr; 
		float rating;
		System.out.println("Please enter the title of the movie you want to leave your rating on: ");
		movieString = sc.next();
		Movie targetMovie = movieMgr.searchMovie(movieString);
		if(targetMovie == null) {
			System.out.println("Please enter a valid movie title!");
		}
		else {
			System.out.println("Enter your thoughts on the movie: ");
			reviewStr = sc.nextLine();
			System.out.println("Enter your rating (0-5): ");
			rating = sc.nextFloat();
			review = new Review(reviewStr, rating);
			targetMovie.addReview(review);
			System.out.println("Your review has been added.");
		}
	}
}

