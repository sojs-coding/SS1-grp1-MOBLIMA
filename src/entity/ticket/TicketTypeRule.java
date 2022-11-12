package entity.ticket;

import java.io.Serializable;

public class TicketTypeRule implements PriceRule, Serializable {
    TicketType ticketType;

    public TicketTypeRule(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getTicketTypes().contains(ticketType))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "TicketTypeRule{" + ticketType + '}';
    }
}
