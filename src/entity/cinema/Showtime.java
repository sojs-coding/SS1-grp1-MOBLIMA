package entity.cinema;

import entity.booking.PersonAge;
import entity.booking.Ticket;
import entity.movie.Movie;

import java.io.Serializable;
import java.time.LocalDateTime; // Import the LocalDateTime class


public class Showtime implements Serializable {

	private LocalDateTime datetime;
	private Layout layout;

	private Cinema cinema;

	public LocalDateTime getDateTime() {
		return datetime;
	}

	public Showtime(LocalDateTime datetime, Cinema cinema, Movie movie) {
		this.datetime = datetime;
		this.cinema = cinema;
		this.layout = cinema.cloneCinemaLayout();
	}

	public Ticket produceTicket(PersonAge age, int row, int column) {
		if (layout.occupy(row, column)) {
			Ticket ticket = new Ticket(row, column, age);
			return ticket;
		}
		return null;
	}

	public void destroyTicket(Ticket ticket) {
		layout.free(ticket.getRow(), ticket.getColumn());
	}

	public void displaySeating() {
		layout.displayLayout();
	}

	public Cinema getCinema() {
		return cinema;
	}
}