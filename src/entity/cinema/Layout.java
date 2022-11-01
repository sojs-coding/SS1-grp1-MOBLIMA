package entity.cinema;
/**
 Represents the Layout of a Cinema.
 A layout shall only be part of 1 Cinema.
 @author Samuel Ong
 @version 1.1
 @since 2022-10-31
 */
public class Layout {
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
				if (other.layout[i][j] != null) {
					layout[i][j] = new LayoutObject(other.layout[i][j]);
				}
			}
		}
	}

    /**
	 * Displays the layout using the Cinema objects in the layout
	 */
	public void displayLayout() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (layout[i][j] != null) {
					if (layout[i][j].isOccupied()) {
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
	public boolean setObject(int row, int column, LayoutObject layoutObject) {
		for (int i = 0; i < layoutObject.getSize(); i++) {
			if (layout[row][column+i] != null) {
				return false;
			}
		}
		for (int i = 0; i < layoutObject.getSize(); i++) {
			layout[row][column+i] = layoutObject;
		}
		return true;
	}
	/**
	 * Occupies the Cinema Object in the specified row and column
	 * @param row The row index
	 * @param column The column index
	 */
	public boolean occupy(int row, int column) {
		if (layout[row][column].isOccupied()) {
			return false;
		}
		layout[row][column].occupy();
		return true;
	}
	/**
	 * Frees up the Cinema Object in the specified row and column
	 * @param row The row index
	 * @param column The column index
	 */
	public void free(int row, int column) {
		layout[row][column].free();
	}

	public void confirm() {
		// TODO - implement CinemaLayout.confirm
		throw new UnsupportedOperationException();
	}
}