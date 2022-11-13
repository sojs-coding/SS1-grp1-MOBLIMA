package entity.ticket;

/**
 Represents the After 6pm Rule for Ticket Condition
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public interface PriceRule {
    /**
     * @param ticket Ticket being evaluated
     * @return Returns whether the condition was a success
     */
    boolean isValid(Ticket ticket);
}
