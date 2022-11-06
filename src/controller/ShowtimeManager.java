package controller;

import entity.cinema.Showtime;
import java.io.Serializable;
import java.util.ArrayList;
/**
 Represents the Showtime Manager
 @author Samuel Ong
 @version 1.0
 @since 2022-11-03
 */
public class ShowtimeManager implements Serializable {
/**
 * A list of showtime
 */
	private ArrayList<Showtime> showtimes;

	/**
	 * Constructor of ShowtimeManager
	 * Instantiates a list of showtime
	 */
	public ShowtimeManager() {
		showtimes = new ArrayList<Showtime>();
	}

	public ArrayList<Showtime> getShowtimes() {
		return showtimes;
	}

	/**
	 * 
	 * @param showtime
	 */


	public void addShowtime(Showtime showtime) {
		showtimes.add(showtime);
	}

	/**
	 *
	 * @param id
	 */
	public void removeShowtime(int id) {
		try {
			showtimes.remove(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("No showtime at index %d", id);
		}
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Showtime getShowtime(int id) {
		try {
			return showtimes.get(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("No showtime at index %d", id);
		}
		return null;
	}

}