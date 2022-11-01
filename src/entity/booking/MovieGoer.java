package entity.booking;
public class MovieGoer {

	private String name;
	/**
	 * Contains 8
	 */
	private char[] mobile;
	private String email;

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
		this.mobile = mobile;
	}

	public void setName(String name) {
		this.name = name;
	}

}