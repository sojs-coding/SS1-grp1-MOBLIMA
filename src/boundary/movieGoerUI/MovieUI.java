package boundary.movieGoerUI;
import controller.MovieManager;
import entity.movie.Movie;
import entity.movie.Review;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieUI {

	Scanner sc = new Scanner(System.in);
	private MovieManager movieMgr;

	/**
	 * Constructor of MovieUI
	 * @param movieMgr
	 */
	MovieUI(MovieManager movieMgr){
		this.movieMgr = movieMgr;
	}

	/**
	 * Print all the movies that are in the database
	 */
	public void printAllMovies() {
		for(int i = 0; i < movieMgr.getMovies().size(); i++) {
			Movie movie = movieMgr.getMovie(i);
			String title = movie.getTitle();
			System.out.printf("%d) %s",i+1, title);	
			System.out.println();
		}
	}

	/**
	 * Request a string from the user and search and display the movie title that matches the string
	 */
	public void searchMovie() {
		String movieString;
		String movieTitle;
		int j = 0;
		System.out.println("Please enter a string: ");
		try{
			movieString = sc.nextLine();
			movieString = "(.*)" + movieString + "(.*)" ;
			for(j = 0; j < movieMgr.getMovies().size(); j++) {
				movieTitle = movieMgr.getMovie(j).getTitle();
				if(movieTitle.matches(movieString)){
					System.out.println("The movie "+ movieTitle + " exists.");
					return;
				}
			}
		} catch(Exception e){
			System.out.println("Invalid Entry");
			return;
		}
		
		System.out.println("There is no movie with that string.");
	}

	/**
	 * Request the full movie title from the user and display the details of the movie
	 */
	public void viewMovieDetails() {
		String movieTitle;
		System.out.println("Please enter the full title of the movie: ");
		movieTitle = sc.nextLine();
		Movie movie = movieMgr.searchMovie(movieTitle);
		StringBuilder sb = new StringBuilder(movie.getSynopsis());
		int i = 0;
		while (i + 120 < sb.length() && (i = sb.lastIndexOf(" ", i + 120)) != -1) {
			sb.replace(i, i + 1, "\n");
		}
		String[] casts = movie.getCasts();
		ArrayList<Review> reviews = movie.getReviews();
		System.out.println("Title: " + movie.getTitle());
		System.out.print("Casts: ");
		for(int j = 0; j < casts.length; j++) {
			System.out.print(casts[j] + ", ");
		}
		System.out.println();
		System.out.println("Director: " + movie.getTitle());
		System.out.println("Synopsis: " + sb.toString());
		System.out.println("Movie Type: " + movie.getType().toString());
		System.out.print("Reviews: ");
		for(int j = 0; j < reviews.size(); j++) {
			Review review = reviews.get(j);
			System.out.printf(review.getReview() + " Rating: %d/5",review.getRating());
			System.out.println();
		}
		System.out.println("Status: " + movie.getStatus());
	}

	/**
	 * Request the title, and then the review and rating of the movie from the user
	 */
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

