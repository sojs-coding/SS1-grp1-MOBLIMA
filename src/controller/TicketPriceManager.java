package controller;

import entity.ticket.Ticket;
import entity.ticket.TicketRule;

import java.io.Serializable;
import java.util.ArrayList;

/**
 Represents the Manager that handles the TicketPrices
 @author Samuel Ong
 @version 1.0
 @since 2022-11-03
 */
public class TicketPriceManager implements Serializable {
    /**
     * Represents the list of Rules for a certain Pricing
     */
    private ArrayList<TicketRule> ticketRules;
    /**
     * Represents the default Price if none of the Rules were passed
     */
    private double defaultPrice;

    /**
     * The Constructor for the TicketPriceManager
     * @param defaultPrice The default price
     */
    public TicketPriceManager(double defaultPrice) {
        ticketRules = new ArrayList<TicketRule>();
        this.defaultPrice = defaultPrice;
    }

    /**
     * The Method to add the TicketRule for a certain Price
     * TicketRule is appended to the end
     * @param ticketRule The TicketRule being added
     */
    public void addRule(TicketRule ticketRule) {
        ticketRules.add(ticketRule);
    }

    /**
     * The Method to add the TicketRule for a certain Price
     * TicketRule is appended to the index
     * Every other TicketRule is pushed down
     * @param ticketRule The TicketRule being added
     * @param index The index whereby the TicketRule is added to
     */
    public void addRule(TicketRule ticketRule, int index) {
        try {
            ticketRules.add(index, ticketRule);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Failed to add rule! Index out of bounds!\n");
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get the TicketRules stored
     * @return ArrayList<TicketRule>
     */
    public ArrayList<TicketRule> getTicketRules() {
        return ticketRules;
    }

    /**
     * Get the Price of the Ticket using the Rules
     * If none of the rules is valid, returns the default price
     * @param ticket The ticket being evaluated
     * @return Price of the Ticket
     */
    public double getPrice(Ticket ticket) {
        TicketRule ticketRule;
        for (int i = 0; i < ticketRules.size(); i++) {
            ticketRule = ticketRules.get(i);
            if (ticketRule.isValid(ticket)) {
                return ticketRule.getPrice();
            }
        }
        return defaultPrice;
    }
}
