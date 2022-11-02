package entity.booking;
import java.io.Serial;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Payment implements Serializable {

	/*XXXYYYYMMDDhhmm */
	private String tid;
	private char[] cinemaCode;

	public Payment(char[] cinemaCode){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYMMddHHmm");  
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Your TID is: " + this.cinemaCode.toString() + dtf.format(now));
		this.tid = dtf.format(now);
		this.cinemaCode = cinemaCode;
	}

	public String getTid() {
		return this.tid;
	}
}