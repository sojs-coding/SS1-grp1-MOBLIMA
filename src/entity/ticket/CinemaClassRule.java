package entity.ticket;

import entity.booking.PriceRule;
import entity.cinema.CinemaClass;

public class CinemaClassRule implements PriceRule {
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
