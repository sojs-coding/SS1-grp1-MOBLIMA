package entity.cinema;
/**
 Represents the Layout of a Cinema.
 A layout shall only be part of 1 Cinema.
 @author Samuel Ong
 @version 1.0
 @since 2022-10-31
 */
public class CinemaLayout {
	/**
	 * The total length of the layout on the x-axis
	 */
	private int column;
	/**
	 * The total length of the layout on the y-axis
	 */
	private int row;
	/**
	 * This is a 2 dimension array to store each CinemaObject
	 */
	private CinemaObject[][] layout;

	/**
	 * This creates a new Cinema layout with the given size
	 * @param row The number of rows for the layout y-axis
	 * @param column The number of columns for the layout x-axis
	 */
	CinemaLayout(int row, int column) {
		layout = new CinemaObject[row][column];
	}

	/**
	 * Displays the layout using the Cinema objects in the layout
	 */
	public void displayLayout() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.printf("%c", layout[i][j]);
			}
		}
	}

//	/**
//	 *
//	 * @param row
//	 * @param column
//	 */
//	public void occupy(int row, int column) {
//		// TODO - implement CinemaLayout.occupy
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param row
//	 * @param column
//	 */
//	public void free(int row, int column) {
//		// TODO - implement CinemaLayout.free
//		throw new UnsupportedOperationException();
//	}
//
//	public void confirm() {
//		// TODO - implement CinemaLayout.confirm
//		throw new UnsupportedOperationException();
//	}

//	public Object clone() throws CloneNotSupportedException
//	{
//		CinemaLayout cinemaLayout = (CinemaLayout) super.clone();
//		cinemaLayout.layout = new CinemaObject[row][column];
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < column; j++) {
//				cinemaLayout[row][column] = new CinemaObject()
//			}
//		}
//	}
}