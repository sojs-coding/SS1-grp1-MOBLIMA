package entity.ticket;

import entity.booking.PriceRule;
import entity.cinema.CinemaClass;

public class PlatinumMovieRule implements PriceRule {
    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getCinema().getCinemaClass() == CinemaClass.PLATINUM_MOVIE_SUITES)
            return true;
        return false;
    }
}
