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
		String tid = new String(cinemaCode) + dtf.format(now);
		System.out.println("Your TID is: " + tid);
		this.tid = tid;
		this.cinemaCode = cinemaCode;
	}

	public String getTid() {
		return this.tid;
	}
}