package entity.movie;

import java.io.Serializable;

public class Review implements Serializable {

	private String review;
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
