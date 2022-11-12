package entity.ticket;

import entity.booking.PriceRule;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketRule implements Serializable {
    private ArrayList<PriceRule> priceRules;
    private double price;

    public TicketRule(double price) {
        this.price = price;
        this.priceRules = new ArrayList<>();
    }

    public void addRule(PriceRule priceRule) {
        priceRules.add(priceRule);
    }

    public void removeRule(PriceRule priceRule) {
        for (int i = 0; i < priceRules.size(); i++) {
            try {
                if (priceRules.get(i).getClass().equals(priceRule.getClass())) {
                    priceRules.remove(i);
                }
            } catch (IndexOutOfBoundsException e){

            }
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public boolean isValid(Ticket ticket) {
        for (int i = 0; i < priceRules.size(); i++) {
            if (!priceRules.get(i).isValid(ticket))
                return false;
        }
        return true;
    }

    public static TicketRule copyTicketRule(TicketRule other) {
        TicketRule ticketRule = new TicketRule(other.getPrice());
        for (int i = 0; i < other.priceRules.size(); i++) {
            ticketRule.addRule(other.priceRules.get(i));
        }
        return ticketRule;
    }

    @Override
    public String toString() {
        return "TicketRule{" + priceRules + '}';
    }
}
