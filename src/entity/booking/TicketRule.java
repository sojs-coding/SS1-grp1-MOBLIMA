package entity.booking;

import java.util.ArrayList;

public class TicketRule {
    private ArrayList<PriceRule> priceRules;
    private double price;

    public TicketRule(double price) {
        this.price = price;
        this.priceRules = new ArrayList<>();
    }

    public void addRule(PriceRule priceRule) {
        priceRules.add(priceRule);
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
}
