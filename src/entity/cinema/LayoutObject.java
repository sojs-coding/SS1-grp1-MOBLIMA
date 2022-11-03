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
	//private int row;
	//private int column;
	/**
	 * Constructor of LayoutObject
	 * @param isOccupied Whether the object is occupied
	 * @param displayChar The character to be displayed for this object
	 * @param size Size of the object on the x-axis
	 */
	LayoutObject(boolean isOccupied, char displayChar, int size) {
		this.occupied = isOccupied;
		this.displayChar = displayChar;
		this.size = size;
	}
	/**
	 * Constructor copy
	 * @param other The other LayoutObject being copied
	 */
	LayoutObject(LayoutObject other) {
		this.occupied = other.occupied;
		this.displayChar = other.displayChar;
		this.size = other.size;
	}
	/**
	 * To retrieve the display character
	 */
	protected char getSeatSymbol() {
		return displayChar;
	}
	/**
	 * To return the size of the object
	 */
	protected int getSize() {
		return size;
	}
	/**
	 * To return the occupancy
	 */
	protected boolean isOccupied() {
		return occupied;
	}
	/**
	 * To occupy the object
	 */
	protected void occupy() {
		occupied = true;
	}
	/**
	 * To free up the occupancy
	 */
	protected void free() {
		occupied = false;
	}
}