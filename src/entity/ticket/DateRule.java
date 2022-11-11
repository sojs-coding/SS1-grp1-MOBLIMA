package entity.ticket;

import entity.booking.PriceRule;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class DateRule implements PriceRule {
    private ArrayList<DayOfWeek> days;

    public DateRule(ArrayList<DayOfWeek> days) {
        this.days = days;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        for (int i = 0; i < days.size(); i++) {
            if (ticket.getShowtime().getDateTime().getDayOfWeek() == days.get(i))
                return true;
        }
        return false;
    }
}
