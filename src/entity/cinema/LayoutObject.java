package entity.cinema;

import java.io.Serializable;

/**
 Represents an object in a cinemaLayout.
 A CinemaObject is kept in only 1 cinemaLayout.
 @author Samuel Ong
 @version 1.1
 @since 2022-10-31
 */
public class LayoutObject implements Serializable {
	/**
	 * The occupancy of each
	 */
	private boolean occupied;
	/**
	 * The character used to display the object
	 */
	private char displayChar;
	/**
	 * Size of the object
	 */
	private int size;
	private int row;
	private int column;
	LayoutObject(boolean isOccupied, char displayChar, int size) {
		this.occupied = isOccupied;
		this.displayChar = displayChar;
		this.size = size;
	}
	LayoutObject(LayoutObject other) {
		this.occupied = other.occupied;
		this.displayChar = other.displayChar;
		this.size = other.size;
	}
	protected char getSeatSymbol() {
		return displayChar;
	}

	protected int getSize() {
		return size;
	}

	protected boolean isOccupied() {
		return occupied;
	}

	protected void occupy() {
		occupied = true;
	}

	protected void free() {
		occupied = false;
	}
}