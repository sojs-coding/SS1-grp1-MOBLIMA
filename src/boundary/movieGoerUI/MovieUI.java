package boundary.movieGoerUI;

import com.sun.jdi.InvalidTypeException;
import controller.MovieManager;
import entity.movie.Movie;
import entity.movie.Review;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieUI {

	Scanner sc = new Scanner(System.in);
	private MovieManager movieMgr;
	
	MovieUI(MovieManager movieMgr){
		this.movieMgr = movieMgr;
	}
	
	public void printAllMovies() {
		for(int i = 0; i < movieMgr.getMovies().size(); i++) {
			Movie movie = movieMgr.getMovie(i);
			String title = movie.getTitle();
			System.out.printf("%d) %s",i+1, title);	
			System.out.println();
		}
	}
	
	public void searchMovie() {
		String movieString;
		int count = 1;
		System.out.println("Please enter a string: ");
		movieString = sc.nextLine();
		for(int i = 0; i < movieMgr.getMovies().size(); i++) {
			String movieTitle = movieMgr.getMovie(i).getTitle();
			for(int j = 0; j < movieTitle.length() - movieString.length(); j++) {
				String strCompare = movieTitle.substring(j, j+movieString.length());
				if(strCompare.compareToIgnoreCase(movieString) == 0) {
					System.out.printf("%d) %s",count++, movieTitle);
					System.out.println();
					break;
				}
			}
		}
		if(count == 1)
			System.out.println("There is no movie with that string.");
	}
	
	public void viewMovieDetails() {
		String movieTitle;
		System.out.println("Please enter the full title of the movie: ");
		movieTitle = sc.nextLine();
		Movie movie = movieMgr.searchMovie(movieTitle);
		String[] casts = movie.getCasts();
		ArrayList<Review> reviews = movie.getReviews();
		System.out.println("Title: " + movie.getTitle());
		System.out.print("Casts: ");
		for(int i = 0; i < casts.length; i++) {
			System.out.print(casts[i] + ", ");
		}
		System.out.println();
		System.out.println("Director: " + movie.getTitle());
		System.out.println("Synopsis: " + movie.getSynopsis());
		System.out.println("Movie Type: " + movie.getType().toString());
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
		int rating;
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
			String temp = sc.nextLine();
			try {
				rating = Integer.parseInt(temp);
				review = new Review(reviewStr, rating);
				targetMovie.addReview(review);
				System.out.println("Your review has been added.");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input given. Please try again.");
			}
		}
	}
}

