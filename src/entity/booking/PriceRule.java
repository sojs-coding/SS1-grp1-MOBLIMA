package entity.booking;

import entity.ticket.Ticket;

public interface PriceRule {
    boolean isValid(Ticket ticket);
}
