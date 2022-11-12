package entity.booking;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 Represents a Payment under a booking
 @author Marcus Yeo
 @version 1.1
 @since 2022-11-01
 */
public class Payment implements Serializable {

	/*
	* Represents the transcation in the form of XXXYYYYMMDDhhmm
	*/
	private String tid;
	/*
	* Represents the cinemaCode of the cinema 
	*/
	private char[] cinemaCode;
    /**
	 * Constructor for Payment
	 * @param cinemaCode The cinemaCode of the movie to be watched
	 */
	public Payment(char[] cinemaCode){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYMMddHHmm");  
		LocalDateTime now = LocalDateTime.now();
		String tid = new String(cinemaCode) + dtf.format(now);
		System.out.println("Your TID is: " + tid);
		this.tid = tid;
		this.cinemaCode = cinemaCode;
	}
   /**
	 * @returns the TID of the payment
	 */	
	public String getTid() {
		return this.tid;
	}
}