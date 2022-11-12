package entity.booking;

import entity.cinema.Showtime;
import java.io.Serializable;
import java.util.ArrayList;

import controller.TicketPriceManager;
import entity.ticket.Ticket;

public class Booking implements Serializable {
    private Showtime central;
    private MovieGoer user;
    private Payment payment;
    private ArrayList<Ticket> ticket;
    private char[] phoneNumber;
    private int id;
    private float totalPrice = 0;

    public Booking(Showtime central, MovieGoer movieGoer, Payment payment, ArrayList<Ticket> ticket, int id, char[] p){
        this.central = central;
        this.user = movieGoer;
        this.phoneNumber = p;
        this.payment = payment;
        this.ticket = ticket;
        this.id = id;
    }

    /*If no booking can be found in BookingManager, this is returned*/
    public Booking(){
        this.user =null;
        this.payment = null;
        this.ticket = null;
        this.id = -1;
    }

    public int getId(){
        return this.id;
    }

    public Payment getPayment(){
        return this.payment;
    }

    public MovieGoer getUser() {
        return user;
    }

    public ArrayList<Ticket> getTicket() {
        return ticket;
    }

    public Showtime getCentral() {
        return central;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setUser(MovieGoer user) {
        this.user = user;
    }

    public void setTicket(ArrayList<Ticket> ticket) {
        this.ticket = ticket;
    }

    public void setCentral(Showtime central) {
        this.central = central;
    }

    public double calculateTotalPrice(){
        double totalPrice = 0;
        for(int i = 0;i < this.ticket.size(); i++){
            totalPrice += ticket.get(i).getPrice();
        }

        return totalPrice;
    }

    public char[] getPhoneNumber() {
        return phoneNumber;
    }
}