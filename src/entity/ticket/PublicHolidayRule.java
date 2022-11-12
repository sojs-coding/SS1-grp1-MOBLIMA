package entity.ticket;

import controller.Initialization;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeSet;

public class PublicHolidayRule implements PriceRule, Serializable {
    /**
     * @param ticket
     * @return
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

    @Override
    public String toString() {
        return "PublicHolidayRule";
    }
}
