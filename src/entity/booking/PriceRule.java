package entity.booking;

public interface PriceRule {
    boolean isValid(Ticket ticket);
}
