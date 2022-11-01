package entity.cinema;
/**
 Represents an abstract object in a cinemaLayout.
 A CinemaObject is kept in only 1 cinemaLayout.
 @author Samuel Ong
 @version 1.0
 @since 2022-10-31
 */
public abstract class CinemaObject {
	/**
	 * The occupancy of each
	 */
	private boolean occupied;

	public void displaySeat() {
		// TODO - implement CinemaObject.displaySeat
		throw new UnsupportedOperationException();
	}

	public boolean occupy() {
		// TODO - implement CinemaObject.occupy
		throw new UnsupportedOperationException();
	}

	public void free() {
		// TODO - implement CinemaObject.free
		throw new UnsupportedOperationException();
	}


}