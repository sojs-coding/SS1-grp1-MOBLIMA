package entity.booking;

import java.io.Serializable;

public class MovieGoer implements Serializable {

	private String name;
	/**
	 * Contains 8
	 */
	private char[] mobile;
	private String email;
	private PersonAge age;

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

	public PersonAge getAge() {
		return age;
	}
	public void setAge(PersonAge age) {
		this.age = age;
	}
}