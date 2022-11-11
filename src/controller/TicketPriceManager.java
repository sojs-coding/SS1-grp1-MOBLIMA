package controller;

import entity.ticket.Ticket;
import entity.ticket.TicketRule;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketPriceManager implements Serializable {
    private ArrayList<TicketRule> ticketRules;

    private double defaultPrice;

    public TicketPriceManager(double defaultPrice) {
        ticketRules = new ArrayList<TicketRule>();
        this.defaultPrice = defaultPrice;
    }

    public void addRule(TicketRule ticketRule) {
        ticketRules.add(ticketRule);
    }

    public void addRule(TicketRule ticketRule, int index) {
        try {
            ticketRules.add(index, ticketRule);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Failed to add rule! Index out of bounds!\n");
            throw new IndexOutOfBoundsException();
        }
    }

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
