package entity.ticket;

import entity.cinema.CinemaClass;

import java.io.Serializable;

public class CinemaClassRule implements PriceRule, Serializable {
    private CinemaClass cinemaClass;

    public CinemaClassRule(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getCinema().getCinemaClass() == cinemaClass)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "CinemaClassRule{" + cinemaClass + '}';
    }
}
