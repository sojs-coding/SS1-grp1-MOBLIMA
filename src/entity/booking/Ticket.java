package entity.booking;
public class Ticket {

	private int seatId;
	private PersonAge age;

	public PersonAge getAge() {
		return age;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setAge(PersonAge age) {
		this.age = age;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
}