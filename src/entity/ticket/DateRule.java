package entity.ticket;

import entity.booking.PriceRule;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class DateRule implements PriceRule, Serializable {
    private AbstractCollection<DayOfWeek> days;

    public DateRule(AbstractCollection<DayOfWeek> days) {
        this.days = days;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public boolean isValid(Ticket ticket) {
        for (int i = 0; i < days.size(); i++) {
            if (ticket.getShowtime().getDateTime().getDayOfWeek() == days.toArray()[i])
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DateRule{" + days + '}';
    }
}
