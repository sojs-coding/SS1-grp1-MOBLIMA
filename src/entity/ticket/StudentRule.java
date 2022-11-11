package entity.ticket;

import entity.booking.PriceRule;

public class StudentRule implements PriceRule {
    /**
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getTicketTypes().contains(TicketType.STUDENT))
            return true;
        return false;
    }
}
