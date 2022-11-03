import java.io.Serializable;

public class MovieReview implements Serializable {

	private String review;
	private int rating;
	
	MovieReview(String review, int rating) {
		
		this.review = review;
		this.rating = rating;
	}
	
	public String getReview(){
		return review;
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public void setReview(int rating) {
		this.rating = rating;
	}

}
