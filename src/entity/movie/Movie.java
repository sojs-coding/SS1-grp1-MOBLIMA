package entity.movie;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Represents a movie in a cinema
 */
public class Movie implements Serializable {

    /**
	 * Constructor for the Movie class
	 * @param title title of the movie
	 * @param status status of the movie
	 * @param synopsis synopsis is a brief summary of the movie
	 * @param d the overallrating of the movie
	 * @param type the type of movie
	 * @param director the director of the movie
	 * @param casts the casts starring in the movie
	 */
	public Movie(String title, ShowingStatus status, String synopsis, double d, MovieType type, String director, String[] casts) {
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.overallRating = d;
		this.type = type;
		this.director = director;
		this.casts = casts;
		this.reviews = new ArrayList<Review>();
	}
    /**
	 * title of the movie
	 */
	private String title; 
	/**
	 * status of the movie
	 */
	private ShowingStatus status;
	/**
	 * synopsis is a brief summary of the movie
	 */
	private String synopsis;
	/**
	 * average rating of the movie based on user ratings
	 */
	private double overallRating;
	/**
	 * type of the movie
	 */
	private MovieType type;
	/**
	 * the director of the movie
	 */
	private String director;
	/**
	 * the casts starring in the movie
	 */
	private String[] casts;
	/**
	 * the reviews by users who have watched the movie
	 */
	private ArrayList<Review> reviews;

	/**
	 * Accessor for String variable title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Modifier for String variable title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Accessor for String variable synopsis
	 * @return synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Modifier for String variable synopsis
	 * @param synopsis
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Accessor for enum variable status
	 * @return status
	 */
	public ShowingStatus getStatus() {
		return status; // Why did I do it this way? Don't go returning a variable in a different form.
		// They can use ShowingStatus.COMING_SOON. They don't need you to convert it into a String

		/*switch(status) {
		case COMING_SOON:
			return "Coming soon";
		case PREVIEW:
			return "Preview";
		case NOW_SHOWING:
			return "Not Showing";
		case END_OF_SHOWING:
			return "Showing Ended";
		}
		return "Not in switch function.";*/
	}

	/**
	 * Modifier for enum variable status
	 * @param status
	 */
	public void setStatus(ShowingStatus status) {
		this.status = status;
	}

	/**
	 * Accessor for double variable overallRating
	 * @return overallRating
	 */
	public double getOverallRating() {
		return overallRating;
	}

	/**
	 * Modifier for double variable overallRating
	 * @param overallRating
	 */
	public void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}

	/**
	 * Accessor for enum variable type
	 * @return type
	 */
	public MovieType getType() {
		return type;
	}

	/**
	 * Modifier for enum variable type
	 * @param type
	 */
	public void setType(MovieType type) {
		this.type = type;
	}

	/**
	 * Accessor for String variable director
	 * @return director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Modifier for String variable director
	 * @param director
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Accessor for String Array variable casts
	 * @return casts
	 */
	public String[] getCasts() {
		return casts;
	}

	/**
	 * Modifier for String Array variable casts
	 * @param casts
	 */
	public void setCasts(String[] casts) {
		this.casts = casts;
	}

	/**
	 * Accessor for ArrayList of review objects
	 * @return reviews
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Accessor for review object in ArrayList of review objects with parameter id
	 * @param id
	 * @return reviews.get(id)
	 */
	public Review getReview(int id) {
		return reviews.get(id);
	}

	/**
	 * Add a review object into the ArrayList of review objects
	 * @param review
	 */
	public void addReview(Review review) {
		reviews.add(review);
	}

	/**
	 * Set a review object into the ArrayList of review objects into the parameter id
	 * @param review
	 * @param id
	 */
	public void setReview(Review review, int id) {
		reviews.set(id, review);
	}
	
/*	public void printMovie() {
		System.out.println("Title: " + title);
		System.out.print("Casts: ");
		for(int i = 0; i < casts.length; i++) {
			System.out.print(casts[i] + ", ");
		}
		System.out.println();
		System.out.println("Director: " + title);
		System.out.println("Synopsis: " + synopsis);
		System.out.println("Movie Type: ");
		System.out.print("Reviews: ");
		for(int i = 0; i < reviews.size(); i++) {
			Review review = reviews.get(i);
			System.out.printf(review.getReview() + " Rating: %d/5",review.getRating());
			System.out.println();
		}
		System.out.println("Status: " + this.getStatus());
	}*/
}
