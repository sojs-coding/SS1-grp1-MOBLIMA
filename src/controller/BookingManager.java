package controller;
import entity.booking.*;

import java.io.Serializable;
import java.util.*;

public class BookingManager implements Serializable {

	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	/**
	 * 
	 * @param booking
	 */
	public void addBooking(Booking booking) {
		/*Add booking to list of bookings*/ 
		try {
			bookings.add(booking);
		} catch (UnsupportedOperationException e) {
			System.out.println("Invalid booking entered...");
		}
	}
	/**
	 * 
	 * @param id
	 */
	public void removeBooking(int id) {
		try {
			boolean remove = false;
			Iterator<Booking> iter = bookings.iterator();
			while(iter.hasNext()){
				Booking temp = iter.next();
				if(temp.getId() == id){
					bookings.remove(temp);
					remove = true;
					System.out.println("Removal was successful...");
					return;
				}
			}
			if(!remove){
				System.out.println("Removal was unsuccessful...");
				System.out.println("Invalid id entered...");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error...");
		}
	}

	public Booking getBooking(int id) {
		Booking temp = new Booking();
		try {
			Iterator<Booking> iter = bookings.iterator();
			while(iter.hasNext()){
				Booking curr = iter.next();
				if(curr.getId() == id){
					/*Success case */
					System.out.println("Retrieval was successful..."); 
					return curr;
					}
				}
			} catch (IndexOutOfBoundsException e) {
				// TODO: handle exception
				System.out.println("Error..."); /*Anything that happens */
			}
		System.out.println("Retrieval was unsuccessful...");
		return temp; /*If booking cannot be retrieved */
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
}