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
    private int id;
    private float totalPrice = 0;
    private TicketPriceManager manager;

    public Booking(Showtime central, MovieGoer movieGoer, Payment payment, ArrayList<Ticket> ticket, int id, TicketPriceManager ticketPriceManager){
        this.central = central;
        this.user = movieGoer;
        this.payment = payment;
        this.ticket = ticket;
        this.id = id;
        this.manager = ticketPriceManager;
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

    public float calculateTotalPrice(){
        float totalPrice = 0;
        for(int i = 0;i < this.ticket.size(); i++){
            totalPrice += manager.getPrice(ticket.get(i));
        }

        return totalPrice;
    }
}