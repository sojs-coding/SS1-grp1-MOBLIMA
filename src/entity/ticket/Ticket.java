package entity.ticket;

import entity.cinema.Showtime;
import entity.ticket.TicketType;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {
	private Showtime showtime;
	private ArrayList<TicketType> ticketTypes;
	private int row;
	private int column;

	public Ticket(int row, int column, Showtime showtime, ArrayList<TicketType> ticketTypes) {
		this.showtime = showtime;
		this.ticketTypes = ticketTypes;
		this.row = row;
		this.column = column;
	}

	public ArrayList<TicketType> getTicketTypes() {
		return ticketTypes;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Showtime getShowtime() {
		return showtime;
	}
}