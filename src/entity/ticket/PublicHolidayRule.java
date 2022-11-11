package entity.ticket;

import controller.Initialization;
import entity.booking.PriceRule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PublicHolidayRule implements PriceRule {
    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        ArrayList<LocalDate> localDate = Initialization.getINSTANCE().getHolidayManager().getLocalDateTimeArrayList();
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
}
