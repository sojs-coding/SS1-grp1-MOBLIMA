package entity.movie;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Movie implements Serializable {

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

	private String title;
	private ShowingStatus status;
	private String synopsis;
	private double overallRating;
	private MovieType type;
	private String director;
	private String[] casts;
	private ArrayList<Review> reviews;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
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
	public void setStatus(ShowingStatus status) {
		this.status = status;
	}
	
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}
	
	public MovieType getType() {
		return type;
	}
	public void setType(MovieType type) {
		this.type = type;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String[] getCasts() {
		return casts;
	}
	public void setCasts(String[] casts) {
		this.casts = casts;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public Review getReview(int id) {
		return reviews.get(id);
	}
	public void addReview(Review review) {
		reviews.add(review);
	}
	
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
