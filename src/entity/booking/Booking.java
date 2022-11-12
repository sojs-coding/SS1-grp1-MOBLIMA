package entity.booking;
import entity.cinema.Showtime;
import java.io.Serializable;
import java.util.ArrayList;
import entity.ticket.Ticket;
/**
 Represents a Booking
 @author Marcus Yeo
 @version 1.1
 @since 2022-11-01
 */
public class Booking implements Serializable {
    /**
	 * The showtime of the booking
	 */
    private Showtime central;
    /**
	 * The user of the booking
	 */
    private MovieGoer user;
    /**
	 * The payment proof of the booking
	 */
    private Payment payment;
    /**
	 * The tickets under the booking
	 */
    private ArrayList<Ticket> ticket;
    /**
	 * The phonenumber of the user to verify bookings
	 */
    private char[] phoneNumber;
    /**
	 * The showtime of the booking
	 */
    private int id;
    /**
	 * The totalPrice of the tickets 
	 */
    private float totalPrice = 0;

    /**
	 * Constructor for Booking
	 * @param central   The Booking's showtime
	 * @param movieGoer The Booking's user
     * @param payment   The Booking's payment proof
     * @param ticket    The Booking's tickets
     * @param id        The Booking's ID
     * @param p         The Booking's user phonenumber
	 */
    public Booking(Showtime central, MovieGoer movieGoer, Payment payment, ArrayList<Ticket> ticket, int id, char[] p){
        this.central = central;
        this.user = movieGoer;
        this.phoneNumber = p;
        this.payment = payment;
        this.ticket = ticket;
        this.id = id;
    }

    /**
	 * Constructor for null Booking
	 */
    public Booking(){
        this.user =null;
        this.payment = null;
        this.ticket = null;
        this.id = -1;
    }
	/**
	 * @return the ID
	 */
    public int getId(){
        return this.id;
    }
    /**
	 * @return the payment
	 */
    public Payment getPayment(){
        return this.payment;
    }
    /**
	 * @return the User
	 */
    public MovieGoer getUser() {
        return user;
    }
    /**
	 * @return the tickets
	 */
    public ArrayList<Ticket> getTicket() {
        return ticket;
    }
    /**
	 * @return the showtime
	 */
    public Showtime getCentral() {
        return central;
    }
    /**
	 * @return the ID
	 */
    public void setId(int id) {
        this.id = id;
    }
    /**
   * Sets a payment to the booking
   * @param payment A Payment Object created during purchase of tickets
   */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    /**
   * Sets a user of the booking
   * @param user The user of the system
   */
    public void setUser(MovieGoer user) {
        this.user = user;
    }
    /**
   * Sets tickets of the booking
   * @param ticket Array of tickets in the cart of the purchasing process
   */
    public void setTicket(ArrayList<Ticket> ticket) {
        this.ticket = ticket;
    }
    /**
   * Sets showtime of the booking
   * @param central The showtime of the movie to be watched
   */
    public void setCentral(Showtime central) {
        this.central = central;
    }
    /**
   * Calculates the Total Price of tickets in the booking
   */
    public double calculateTotalPrice(){
        double totalPrice = 0;
        for(int i = 0;i < this.ticket.size(); i++){
            totalPrice += ticket.get(i).getPrice();
        }

        return totalPrice;
    }
    /**
	 * @return the phoneNumber of the booking
	 */
    public char[] getPhoneNumber() {
        return phoneNumber;
    }
}