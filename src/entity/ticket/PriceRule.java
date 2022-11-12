package entity.ticket;

import entity.ticket.Ticket;

public interface PriceRule {
    boolean isValid(Ticket ticket);
}
