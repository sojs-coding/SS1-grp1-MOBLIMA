package entity.cinema;

import java.io.Serializable;

/**
 Represents a Cinema part of the Cineplex brand.
 A Cinema is only under 1 Cineplex
 @author Samuel Ong
 @version 1.1
 @since 2022-10-31
 */
public class Cinema implements Serializable {

	/**
	 * The Class of the Cinema
	 */
	private CinemaClass cinemaClass;

	/**
	 * A code specific to this Cinema
	 * code should only contain 3 Characters
	 */
	private char[] code;

	/**
	 * The layout the Cinema uses
	 */
	private Layout layout;

	/**
	 * Constructor for Cinema
	 * @param code The Cinema's code is validated to be only 3 characters long
	 * @param layout The Cinema's layout
	 */
	public Cinema(char[] code, Layout layout) {
		this.code = new char[3];
		if (code.length != 3) { // code isn't of size 3
			throw new IllegalArgumentException();
		}
		this.code = code;

		this.layout = layout;
	}

	/**
	 * @return the code
	 */
	public char[] getCode() {
		return code;
	}
	/**
	 * Instantiates a CinemaLayout based on the Cinema
	 */
	public Layout cloneCinemaLayout() {
		return new Layout(layout);
	}
}