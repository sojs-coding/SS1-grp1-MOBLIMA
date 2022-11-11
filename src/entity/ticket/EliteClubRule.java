package entity.ticket;

import entity.booking.PriceRule;
import entity.cinema.CinemaClass;

public class EliteClubRule implements PriceRule {
    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getCinema().getCinemaClass() == CinemaClass.ELITE_CLUB)
            return true;
        return false;
    }
}
