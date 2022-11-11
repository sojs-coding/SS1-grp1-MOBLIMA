package entity.booking;

public class ElderlyRule implements PriceRule {
    /**
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getTicketTypes().contains(TicketType.ELDERLY))
            return true;
        return false;
    }
}
