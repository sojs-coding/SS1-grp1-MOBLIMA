package entity.ticket;

import entity.booking.PriceRule;
import entity.cinema.CinemaClass;

public class UltimaRule implements PriceRule {
    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getCinema().getCinemaClass() == CinemaClass.ULTIMA)
            return true;
        return false;
    }
}
