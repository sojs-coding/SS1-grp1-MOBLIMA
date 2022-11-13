package entity.ticket;

import java.io.Serializable;
import java.util.ArrayList;

/**
 Represents the Date Rule for Ticket Condition
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class TicketRule implements Serializable {
    /**
     * Represents an ArrayList of PriceRule for the TicketRule
     * It stores all the conditions that the TicketRule should abide by
     */
    private ArrayList<PriceRule> priceRules;
    /**
     * Represents the price of the TicketRule if all the conditions are passed
     */
    private double price;

    /**
     * Constructor for the TicketRule
     * @param price The price of the TicketRule
     */
    public TicketRule(double price) {
        this.price = price;
        this.priceRules = new ArrayList<>();
    }

    /**
     * Adding PriceRule to the TicketRule
     * @param priceRule The Condition for the TicketRule
     */
    public void addRule(PriceRule priceRule) {
        priceRules.add(priceRule);
    }

    /**
     * Removing of PriceRule from the TicketRule
     * Does not require the original priceRule
     * @param priceRule A priceRule of a certain type
     */
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

    /**
     * Sets the price of the TicketRule
     * @param price The price of the Ticket
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the price of the TicketRule
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param ticket Ticket being evaluated
     * @return Returns whether the ticket passed all the conditions the TicketRule has
     */
    public boolean isValid(Ticket ticket) {
        for (int i = 0; i < priceRules.size(); i++) {
            if (!priceRules.get(i).isValid(ticket))
                return false;
        }
        return true;
    }

    /**
     * Static method to copy TicketRule into a new Instantiated TicketRule
     * Shallow Copy
     * @param other
     * @return
     */
    public static TicketRule copyTicketRule(TicketRule other) {
        TicketRule ticketRule = new TicketRule(other.getPrice());
        for (int i = 0; i < other.priceRules.size(); i++) {
            ticketRule.addRule(other.priceRules.get(i));
        }
        return ticketRule;
    }
    /**
     * @return Return the class in String
     */
    @Override
    public String toString() {
        return "TicketRule{" + priceRules + '}';
    }
}
