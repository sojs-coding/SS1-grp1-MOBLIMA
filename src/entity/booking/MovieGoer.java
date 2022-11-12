package entity.booking;
import java.io.Serializable;

/**
 Represents a customer/user of the system
 @author Marcus Yeo
 @version 1.1
 @since 2022-11-01
 */

public class MovieGoer implements Serializable {
	/**
	 * Name of customer
	 */
	private String name;
	/**
	 * Contains 8 characters, mobile number of customer
	 */
	private char[] mobile;
	/**
	 * Email of customer
	 */
	private String email;
    /**
	 * Constructor for MovieGoer
	 * @param username  Customer's name
	 * @param mobile  	Customer mobile number
     * @param email   	Customer email
	 */
	public MovieGoer(String username, char[] mobile, String email) {
		setEmail(email);
		setMobile(mobile);
		setName(username);
	}
	/**
	 * @return the customer email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the customer mobile number
	 */
	public char[] getMobile() {
		return mobile;
	}
	/**
	 * @return the customer name
	 */
	public String getName() {
		return name;
	}
	/**
	 * set the customer email
	 * @param email New email of customer
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * set the mobile number of customer
	 * @param mobile New mobile number of customer
	 */
	public void setMobile(char[] mobile) {
		// Set up verification. 8 digits only.
		this.mobile = mobile;
	}
	/**
	 * set the customer name
	 * @param name New name of customer
	 */
	public void setName(String name) {
		this.name = name;
	}
}