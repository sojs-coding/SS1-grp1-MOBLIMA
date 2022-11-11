package entity.ticket;

import entity.booking.PriceRule;
import entity.cinema.CinemaClass;

public class DolbyAtmosRule implements PriceRule {
    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getCinema().getCinemaClass() == CinemaClass.DOLBY_ATMOS_2D)
            return true;
        return false;
    }
}
