package entity.ticket;

import entity.cinema.Showtime;
import entity.ticket.TicketType;
import java.io.Serializable;
import java.util.ArrayList;
/**
 Represents a ticket in a booking
 @author Marcus Yeo
 @version 1.1
 @since 2022-11-01
 */
public class Ticket implements Serializable {
	/**
	 * The showtime of the movie
	*/
	private Showtime showtime;
	/**
	 * The type of ticket  Student,Elderly etc
	*/
	private ArrayList<TicketType> ticketTypes;
	/**
	 * The row of the seat of the ticket in the cinema
	*/
	private int row;
	/**
	 * The column of the seat of the ticket in the cinema
	*/
	private int column;
	/**
	 * The price of the ticket
	*/
	private double price = 0;
    /**
	 * Constructor for Ticket
	 * @param row   		The row of the seat of the ticket in the cinema
	 * @param column 		The column of the seat of the ticket in the cinema
     * @param ticketTypes   The type of ticket  Student,Elderly etc
	 */
	public Ticket(int row, int column, Showtime showtime, ArrayList<TicketType> ticketTypes) {
		this.showtime = showtime;
		this.ticketTypes = ticketTypes;
		this.row = row;
		this.column = column;
	}
	/**
	 * @return the type of ticket
	 */
	public ArrayList<TicketType> getTicketTypes() {
		return ticketTypes;
	}
	/**
	 * @return the row of the seat of the ticket in the cinema
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @return the column of the seat of the ticket in the cinema
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @return The showtime of the movie
	 */
	public Showtime getShowtime() {
		return showtime;
	}
	/**
	 * @return the price of ticket
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Changes value of price of the ticket
	 * @param d New price of the ticket
	 */
	public void setPrice(double d) {
		this.price = d;
	}
}