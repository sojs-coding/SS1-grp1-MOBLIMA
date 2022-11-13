package entity.movie;

import java.io.Serializable;
/**
 * Class representing the review of a movie by the user
 */
public class Review implements Serializable {
    
	/**
	 * A formal assessment of the movie the user watched
	 */
	private String review;
	/**
	 * The rating which determines how much the user enjoyed the movie
	 */
	private int rating;

	/**
	 * Constructor of Class Review.
	 * @param review
	 * @param rating
	 */
	public Review(String review, int rating) {
		
		this.review = review;
		this.rating = rating;
	}

	/**
	 * Accessor of String variable review
	 * @return
	 */
	public String getReview(){
		return review;
	}

	/**
	 * Accessor of int variable rating
	 * @return
	 */
	public int getRating(){
		return rating;
	}

	/**
	 * Modifier of String variable review
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Modifier of int variable rating
	 */
	public void setReview(int rating) {
		this.rating = rating;
	}

}
