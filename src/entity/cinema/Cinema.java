package entity.cinema;
/**
 Represents a Cinema part of the Cineplex brand.
 A Cinema is only under 1 Cineplex
 @author Samuel Ong
 @version 1.0
 @since 2022-10-31
 */
public class Cinema {

	private CinemaClass cinemaClass;
	/**
	 * A code specific to this Cinema
	 * code should only contain 3 Characters
	 */
	private char[] code;

	/**
	 * The layout the Cinema uses
	 */
	private CinemaLayout cinemaLayout;

	/**
	 * Instantiates a CinemaLayout based on the Cinema
	 */
	public CinemaLayout getCinemaLayout() {
		return cinemaLayout;
	}

}