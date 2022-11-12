package entity.cinema;

import java.io.Serializable;

/**
 Represents the Layout of a Cinema.
 A layout shall only be part of 1 Cinema.
 @author Samuel Ong
 @version 1.1
 @since 2022-10-31
 */
public class Layout implements Serializable {
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
	private LayoutObject[][] layout;
	/**
	 * This creates a new Cinema layout with the given size
	 * @param row The number of rows for the layout y-axis
	 * @param column The number of columns for the layout x-axis
	 */
	public Layout(int row, int column) {
		this.row = row;
		this.column = column;
		layout = new LayoutObject[row][column];
	}
	/**
	 * This clones a new Cinema layout with the given size
	 * @param other The other Cinema layout being cloned
	 */
	Layout(Layout other) {
		column = other.column;
		row = other.row;
		layout = new LayoutObject[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (other.hasObject(i, j)) {
					setObject(i, j, other.layout[i][j]);
					j += other.layout[i][j].getSize() - 1;
				}
			}
		}
	}

    /**
	 * Displays the layout using the Cinema objects in the layout
	 */
	public void displayLayout() {
		System.out.printf("  ");
		for (int j = 0; j < column; j++) {
			System.out.printf("%d", j);
		}
		System.out.println();
		for (int i = 0; i < row; i++) {
			System.out.printf("%d|", i);
			for (int j = 0; j < column; j++) {
				if (hasObject(i, j)) {
					if (isOccupied(i, j)) {
						System.out.printf("X");
					} else {
						System.out.printf("%c", layout[i][j].getSeatSymbol());
					}
				} else {
					System.out.printf(" ");
				}
			}
			System.out.println();
		}
	}
	/**
	 * Sets an object to the designated row and column
	 * @param row The row index
	 * @param column The column index
	 */
	public boolean setObject(int row, int column, LayoutObject layoutObject) {
		try {
			for (int i = 0; i < layoutObject.getSize(); i++) {
				if (layout[row][column + i] != null) {
					return false;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Layout Object was set out of bounds:" + e);
			return false;
		}
		layout[row][column] = new LayoutObject(layoutObject);
		for (int i = 1; i < layoutObject.getSize(); i++) {
			try {
				layout[row][column + i] = layout[row][column];
			} catch (Exception e) {
				for (int j = i; j >= 0; j--) {
					layout[row][column + i] = null;
				}
				return false;
			}
		}
		return true;
	}
	/**
	 * Occupies the Cinema Object in the specified row and column
	 * @param row The row index
	 * @param column The column index
	 */
	public boolean occupy(int row, int column) {
		if (isOccupied(row, column)) {
			return false;
		}
		layout[row][column].occupy();
		return true;
	}
	/**
	 * Checks if the specified row and column is occupied
	 * @param row The row index
	 * @param column The column index
	 */
	public boolean isOccupied(int row, int column) {
		if (!hasObject(row, column)) {
			return true;
		}
		if (layout[row][column].isOccupied()) {
			return true;
		}
		return false;
	}
	/**
	 * Frees up the Cinema Object in the specified row and column
	 * @param row The row index
	 * @param column The column index
	 */
	public void free(int row, int column) {
		layout[row][column].free();
	}
	/**
	 * Checks if  the specified row and column has an object
	 * @param row The row index
	 * @param column The column index
	 */
	private boolean hasObject(int row, int column) {
		if (layout[row][column] == null) {
			return false;
		}
		return true;
	}
	/**
	 * @return the LayoutObject
	 */
	public LayoutObject[][] getLayoutObject() {
		return layout;
	}
}