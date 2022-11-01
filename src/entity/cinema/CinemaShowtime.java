package entity.cinema;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class CinemaShowtime {

	private LocalDateTime datetime;
	private CinemaLayout cinemaLayout;

	public LocalDateTime getDateTime() {
		return datetime;
	}

	CinemaShowtime(LocalDateTime datetime, CinemaLayout cinemaLayout) {
		this.datetime = datetime;
		this.cinemaLayout = cinemaLayout;
	}
}