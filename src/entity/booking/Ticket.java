package entity.booking;

import java.io.Serializable;

public class Ticket implements Serializable {

	private int row;
	private int column;
	private PersonAge age;

	public Ticket(int row, int column, PersonAge age) {
		this.row = row;
		this.column = column;
		this.age = age;
	}
	public PersonAge getAge() {
		return age;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	//public int getSeatId() { return seatId; }

	public void setAge(PersonAge age) {
		this.age = age;
	}

	//public void setSeatId(int seatId) { this.seatId = seatId; }
}