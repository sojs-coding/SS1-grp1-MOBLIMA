package entity.ticket;

import java.io.Serializable;

/**
 Represents the After 6pm Rule for Ticket Condition
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class After6pmRule implements PriceRule, Serializable {
    /**
     * @param ticket Ticket being evaluated
     * @return Returns whether the condition was a success
     */
    @Override
    public boolean isValid(Ticket ticket) {
        if (ticket.getShowtime().getDateTime().getHour() >= 18)
            return true;
        return false;
    }

    /**
     * @return Return the class in String
     */
    @Override
    public String toString() {
        return "After6pmRule";
    }
}
