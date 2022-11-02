package controller;

import entity.cinema.Showtime;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowtimeManager implements Serializable {

	ArrayList<Showtime> showtimes;

	public ShowtimeManager() {
		showtimes = new ArrayList<Showtime>();
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
		// TODO - implement CinemaShowtimeManager.removeShowtime
		throw new UnsupportedOperationException();
	}

	public Showtime getShowtime(int id) {
		try {
			return showtimes.get(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("No cinema at index %d", id);
		}
		return null;
	}

}