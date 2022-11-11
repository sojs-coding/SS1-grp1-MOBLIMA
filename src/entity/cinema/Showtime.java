package entity.cinema;
import entity.ticket.Ticket;
import entity.ticket.TicketType;
import entity.movie.Movie;
import java.io.Serializable;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.util.ArrayList;

/**
 Represents the Showtime for each movie being shown in the cinema
 @author Samuel Ong
 @version 1.1
 @since 2022-11-01
 */
public class Showtime implements Serializable {
	/**
	 * The date and time of the Movie
	 */
	private LocalDateTime datetime;
	/**
	 * Seating plans for the Movie timeslot
	 */
	private Layout layout;
	/**
	 * The Cinema the movie is held in
	 */
	private Cinema cinema;
	/**
	 * The Movie being shown at that showtime
	 */
	private Movie movie;
	/**
	 * Returns the date and time of the Movie
	 */
	public LocalDateTime getDateTime() {
		return datetime;
	}
	/**
	 * The Constructor of the Showtime
	 * @param datetime The date and time of the Showtime
	 * @param cinema The Cinema the movie is held in
	 * @param movie The Movie being shown
	 */
	public Showtime(LocalDateTime datetime, Cinema cinema, Movie movie) {
		this.datetime = datetime;
		this.cinema = cinema;
		this.layout = cinema.cloneCinemaLayout();
		this.movie = movie;
	}
	/**
	 * Occupies the Seat and
	 * Instantiates a Ticket for the Seat
	 */
	public Ticket produceTicket(int row, int column, ArrayList<TicketType> ticketTypes) {
		if (layout.occupy(row, column)) {
			return new Ticket(row, column, this, ticketTypes);
		}
		return null;
	}
	/**
	 * Frees up the seat
	 */
	public void destroyTicket(Ticket ticket) {
		int row = ticket.getRow();
		int column = ticket.getColumn();
		layout.free(row, column);
	}
	/**
	 * Displays the seating arrangement for this showtime
	 * This is temporary btw. I don't think there should be any displaying of layout done by the entity.
	 */
	public void displaySeating() {
		layout.displayLayout();
	}
	/**
	 * Get the Cinema where the movie is held in
	 */
	public Cinema getCinema() {
		return cinema;
	}
	public Movie getMovie() {
		return movie;
	}

	public Layout getLayout() {
		return layout;
	}
}