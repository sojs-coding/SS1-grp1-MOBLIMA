package entity.booking;

import entity.cinema.Showtime;
import java.io.Serializable;
import entity.booking.MovieGoer;

public class Booking implements Serializable {
    private Showtime central;
    private MovieGoer user;
    private Payment payment;
    private Ticket ticket;
    private int id;

    public Booking(Showtime central, MovieGoer movieGoer, Payment payment, Ticket ticket, int id){
        this.central = central;
        this.user = movieGoer;
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

    public Ticket getTicket() {
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

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setCentral(Showtime central) {
        this.central = central;
    }
}