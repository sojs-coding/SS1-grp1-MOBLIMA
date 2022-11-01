package entity.cinema;

/**
 Represents the class of a Cinema
 @author Samuel Ong
 @version 1.0
 @since 2022-11-1
 */
public class Aisle extends CinemaObject {

	private char displaySymbol;
	Aisle(char displaySymbol) {
		this.displaySymbol = displaySymbol;
	}
	public char getDisplaySymbol() {
		return displaySymbol;
	}

//	public boolean occupy() {
//		// TODO - implement Aisle.occupy
//		throw new UnsupportedOperationException();
//	}
//
//	public void free() {
//		// TODO - implement Aisle.free
//		throw new UnsupportedOperationException();
//	}

}