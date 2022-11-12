package entity.ticket;

import entity.booking.PriceRule;

import java.io.Serializable;

public class Before6pmRule implements PriceRule, Serializable {
    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getDateTime().getHour() < 18)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Before6pmRule";
    }
}
