package entity.ticket;

import entity.cinema.CinemaClass;

import java.io.Serializable;

/**
 Represents the CinemaClass Rule for Ticket Condition
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class CinemaClassRule implements PriceRule, Serializable {
    /**
     * The CinemaClass of the Cinema
     */
    private CinemaClass cinemaClass;

    /**
     * Constructor to pass the cinemaClass
     * @param cinemaClass
     */
    public CinemaClassRule(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * @param ticket Ticket being evaluated
     * @return Returns whether the ticket for a Cinema of a certain CinemaClass was a success
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getCinema().getCinemaClass() == cinemaClass)
            return true;
        return false;
    }
    /**
     * @return Return the class in String
     */
    @Override
    public String toString() {
        return "CinemaClassRule{" + cinemaClass + '}';
    }
}
