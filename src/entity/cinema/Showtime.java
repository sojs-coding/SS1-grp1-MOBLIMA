package entity.cinema;

import java.time.LocalDateTime; // Import the LocalDateTime class


public class Showtime {

	private LocalDateTime datetime;
	private Layout layout;

	public LocalDateTime getDateTime() {
		return datetime;
	}

	Showtime(LocalDateTime datetime, Layout layout) {
		this.datetime = datetime;
		this.layout = layout;
	}
}