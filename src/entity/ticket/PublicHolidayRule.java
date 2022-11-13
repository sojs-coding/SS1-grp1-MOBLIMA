package entity.ticket;

import controller.Initialization;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeSet;

/**
 Represents the Public Holiday Rule for Ticket Condition
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class PublicHolidayRule implements PriceRule, Serializable {
    /**
     * @param ticket Ticket being evaluated
     * @return Returns whether the ticket showtime date is a Public Holiday based on the HolidayManager
     */
    @Override
    public boolean isValid(Ticket ticket) {
        TreeSet<LocalDate> localDate = Initialization.getINSTANCE().getHolidayManager().getLocalDates();
        // Public Holiday
        if (localDate.contains(ticket.getShowtime().getDateTime().toLocalDate()))
        {
            return true;
        }
        // Eve of Public Holiday
        if (localDate.contains(ticket.getShowtime().getDateTime().toLocalDate().minusDays(1)))
        {
            return true;
        }
        return false;
    }
    /**
     * @return Return the class in String
     */
    @Override
    public String toString() {
        return "PublicHolidayRule";
    }
}
