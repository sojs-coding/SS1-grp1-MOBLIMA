package entity.booking;

import java.io.Serializable;
import entity.booking.PersonAge;

public class MovieGoer implements Serializable {

	private String name;
	/**
	 * Contains 8
	 */
	private char[] mobile;
	private String email;

	public MovieGoer(String username, char[] mobile, String email) {
		setEmail(email);
		setMobile(mobile);
		setName(username);
	}

	public String getEmail() {
		return email;
	}

	public char[] getMobile() {
		return mobile;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(char[] mobile) {
		// Set up verification. 8 digits only.
		this.mobile = mobile;
	}

	public void setName(String name) {
		this.name = name;
	}
}