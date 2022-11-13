package entity.ticket;

import java.io.Serializable;

/**
 Represents the TicketType Rule for Ticket Condition
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class TicketTypeRule implements PriceRule, Serializable {
    /**
     * The TicketType stored in this Rule
     */
    TicketType ticketType;

    /**
     * The constructor for the TicketTypeRule
     * @param ticketType The TicketType to be stored
     */
    public TicketTypeRule(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @param ticket Ticket being evaluated
     * @return Returns whether the ticket is of a certain TicketType
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getTicketTypes().contains(ticketType))
            return true;
        return false;
    }
    /**
     * @return Return the class in String
     */
    @Override
    public String toString() {
        return "TicketTypeRule{" + ticketType + '}';
    }
}
