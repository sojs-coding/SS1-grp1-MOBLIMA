package entity.ticket;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.AbstractCollection;

/**
 Represents the Date Rule for Ticket Condition
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class DateRule implements PriceRule, Serializable {
    /**
     * Represents a collection of DayOfWeek objects
     */
    private AbstractCollection<DayOfWeek> days;

    /**
     * Constructor for the DateRule
     * @param days A collection of days to be evaluated on
     */
    public DateRule(AbstractCollection<DayOfWeek> days) {
        this.days = days;
    }

    /**
     * @param ticket Ticket being evaluated
     * @return Returns whether the ticket showtime day exist in the array of days indicated by this rule
     */
    @Override
    public boolean isValid(Ticket ticket) {
        for (int i = 0; i < days.size(); i++) {
            if (ticket.getShowtime().getDateTime().getDayOfWeek() == days.toArray()[i])
                return true;
        }
        return false;
    }
    /**
     * @return Return the class in String
     */
    @Override
    public String toString() {
        return "DateRule{" + days + '}';
    }
}
