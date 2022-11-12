package boundary.staffUI;

import controller.Initialization;
import controller.TicketPriceManager;
import entity.cinema.CinemaClass;
import entity.ticket.*;

import java.time.DayOfWeek;
import java.util.*;

public class TicketConfigurationUI {
    private Scanner sc;
    private TicketPriceManager ticketPriceManager;

    public TicketConfigurationUI() {
        this.ticketPriceManager = Initialization.getINSTANCE().getTicketPriceManager();
        this.sc = new Scanner(System.in);
    }

    public void configure()
    {
        //update ticket price
        String choose;
        do
        {
            System.out.println("How would you like to configure ticket pricing rules?");
            System.out.println("1) Add ticket price: ");
            System.out.println("2) Delete ticket price:");
            System.out.println("3) Update ticket price:");
            System.out.println("0) Exit:");
            choose = sc.nextLine();
            switch(choose)
            {
                case "0":
                    return;
                case "1":
                    //addTicketPrice
                    addTicketPriceRules();
                    break;
                case "2":
                    //deleteTicketPrice
                    deleteTicketPriceRules();
                    break;
                case "3":
                    //updateTicketPrice
                    updateTicketPriceRules();
                    break;
            }
        } while (!choose.equals("0"));
    }

    private void addTicketTypeRule(TicketRule ticketRule) {
        System.out.println(ticketRule.toString());
        ticketRule.removeRule(new TicketTypeRule(TicketType.ELDERLY));
        ticketRule.removeRule(new TicketTypeRule(TicketType.STUDENT));
        String choose;
        System.out.println("Ticket Type: ");
        System.out.println("Press 0 for None");
        System.out.println("Press 1 for Elderly");
        System.out.println("Press 2 for Student");

        do {
            choose = sc.nextLine();
            switch (choose) {
                case "0":
                    return;
                case "1":
                    ticketRule.addRule(new TicketTypeRule(TicketType.ELDERLY));
                    return;
                case "2":
                    ticketRule.addRule(new TicketTypeRule(TicketType.STUDENT));
                    return;
                default:
                    System.out.println("Invalid option! Try again!");
                    break;
            }
        }while (true);
    }

    private void toggleDayOfWeek(EnumSet<DayOfWeek> daysOfWeek, DayOfWeek dayOfWeek) {
        if (daysOfWeek.contains(dayOfWeek))
            daysOfWeek.remove(dayOfWeek);
        else
            daysOfWeek.add(dayOfWeek);
    }

    private void addDaysOfWeekRule(TicketRule ticketRule) {
        System.out.println(ticketRule.toString());
        EnumSet<DayOfWeek> daysOfWeek = EnumSet.noneOf(DayOfWeek.class);
        ticketRule.removeRule(new DateRule(daysOfWeek));
        String choose;
        System.out.println("Day of week: ");
        System.out.println("Press 0 for Exit");
        System.out.println("Press 1 for Monday");
        System.out.println("Press 2 for Tuesday");
        System.out.println("Press 3 for Wednesday");
        System.out.println("Press 4 for Thursday");
        System.out.println("Press 5 for Friday");
        System.out.println("Press 6 for Saturday");
        System.out.println("Press 7 for Sunday");
        do {
            System.out.printf("Select a choice (Toggle) : ");
            choose = sc.nextLine();
            switch (choose) {
                case "0":
                    if (daysOfWeek.isEmpty())
                        return;
                    ticketRule.addRule(new DateRule(daysOfWeek));
                    return;
                case "1":
                    toggleDayOfWeek(daysOfWeek, DayOfWeek.MONDAY);
                    break;
                case "2":
                    toggleDayOfWeek(daysOfWeek, DayOfWeek.TUESDAY);
                    break;
                case "3":
                    toggleDayOfWeek(daysOfWeek, DayOfWeek.WEDNESDAY);
                    break;
                case "4":
                    toggleDayOfWeek(daysOfWeek, DayOfWeek.THURSDAY);
                    break;
                case "5":
                    toggleDayOfWeek(daysOfWeek, DayOfWeek.FRIDAY);
                    break;
                case "6":
                    toggleDayOfWeek(daysOfWeek, DayOfWeek.SATURDAY);
                    break;
                case "7":
                    toggleDayOfWeek(daysOfWeek, DayOfWeek.SUNDAY);
                    break;
                default:
                    System.out.println("Invalid option! Try again!");
                    break;
            }
            System.out.println("Updated Ticket Price Rules : " + daysOfWeek);
        } while (true);
    }

    private void addTimingRule(TicketRule ticketRule) {
        System.out.println(ticketRule.toString());
        ticketRule.removeRule(new Before6pmRule());
        ticketRule.removeRule(new After6pmRule());
        String choose;
        System.out.println("Press 0 for None");
        System.out.println("Press 1 for Before 6pm");
        System.out.println("Press 2 for After 6pm");
        do {
            choose = sc.nextLine();
            switch (choose) {
                case "0":
                    return;
                case "1":
                    ticketRule.addRule(new Before6pmRule());
                    return;
                case "2":
                    ticketRule.addRule(new After6pmRule());
                    return;
                default:
                    System.out.println("Invalid option! Try again!");
                    break;
            }
        } while (true);
    }

    private void addPublicHolidayRule(TicketRule ticketRule) {
        System.out.println(ticketRule.toString());
        ticketRule.removeRule(new PublicHolidayRule());
        String choose;
        System.out.println("Press 0 for None");
        System.out.println("Press 1 for Public Holiday");
        do {
            choose = sc.nextLine();
            switch (choose) {
                case "0":
                    return;
                case "1":
                    ticketRule.addRule(new PublicHolidayRule());
                    return;
                default:
                    System.out.println("Invalid option! Try again!");
                    break;
            }
        } while (true);
    }

    private void addTicketPriceRules() {
        System.out.println("=====================================");
        System.out.println("ADD TICKET PRICE SCREEN");
        System.out.println("=====================================");
        TicketRule ticketRule = new TicketRule(10);

        addTicketTypeRule(ticketRule);
        addDaysOfWeekRule(ticketRule);
        addTimingRule(ticketRule);
        addPublicHolidayRule(ticketRule);
        for (TicketRule t : setClassPrice(ticketRule)) {
            insertTicketRule(t);
        }
    }

    private void setRulePrice(TicketRule ticketRule) {
        String sPrice;
        double price;
        System.out.println("=====================================");
        System.out.println("PRICE OF RULE");
        System.out.println("=====================================");

        do {
            System.out.printf("Current Price : %.2f\n", ticketRule.getPrice());
            System.out.printf("Price for %s\n", ticketRule.toString());
            sPrice = sc.nextLine();

            try {
                price = Double.parseDouble(sPrice);
                price = (double)Math.round(price * 100d) / 100d;
                ticketRule.setPrice(price);
                return;
            } catch (NumberFormatException e) {
                System.out.println("Please return a valid input! Try again!");
            }
        } while(true);
    }

    private List<TicketRule> setClassPrice(TicketRule ticketRule) {
        String sPrice;
        double price;
        ArrayList<TicketRule> ticketRules = new ArrayList<>();
        System.out.println("=====================================");
        System.out.println("PRICE OF CLASS BASED ON THE RULES");
        System.out.println("=====================================");
        System.out.println("Input NA if price does not exist");
        for (CinemaClass cinemaClass : CinemaClass.values()) {
            do {
                System.out.printf("Price for %s\n:", cinemaClass.toString());
                sPrice = sc.nextLine();
                if (sPrice.toLowerCase(Locale.ROOT).equals("na")) {
                    break;
                }
                try {
                    price = Double.parseDouble(sPrice);
                    price = (double)Math.round(price * 100d) / 100d;
                    TicketRule temp = TicketRule.copyTicketRule(ticketRule);
                    temp.addRule(new CinemaClassRule(cinemaClass));
                    temp.setPrice(price);
                    ticketRules.add(temp);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please return a valid input! Try again!");
                }
            } while(true);
        }
        return ticketRules;
    }

    private boolean insertTicketRule(TicketRule ticketRule) {
        String choose;
        System.out.println("=====================================");
        System.out.println("TICKET RULE PRIORITY SCREEN");
        System.out.println("=====================================");
        System.out.println(ticketRule.toString());
        ArrayList<TicketRule> ticketRules = ticketPriceManager.getTicketRules();
        System.out.println("Input -1 to Cancel!");
        for (int i = 0; i < ticketRules.size(); i++) {
            System.out.printf("%d) %s\n", i, ticketRules.get(i));
        }
        System.out.printf("%d) LOWEST\n", ticketRules.size());
        do {
            System.out.printf("Select a Rule : ");
            choose = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choose);
                if ( !(choice >= -1 && choice <= ticketRules.size()) ) {
                    throw new NumberFormatException();
                }
                if (choice == -1) {
                    return false;
                }
                if (choice != ticketRules.size())
                    ticketRules.add(choice, ticketRule);
                else
                    ticketRules.add(ticketRule);
                return true;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid choice! Try again! -1 to %d\n", ticketRules.size());
            }
        } while (true);
    }

    public boolean deleteTicketPriceRules() {
        String choose;
        System.out.println("=====================================");
        System.out.println("TICKET RULE DELETION SCREEN");
        System.out.println("=====================================");

        ArrayList<TicketRule> ticketRules = ticketPriceManager.getTicketRules();
        System.out.println("Input -1 to Cancel!");
        for (int i = 0; i < ticketRules.size(); i++) {
            System.out.printf("%d) %s\n", i, ticketRules.get(i));
        }
        do {
            choose = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choose);
                if ( !(choice >= -1 && choice < ticketRules.size()) ) {
                    throw new NumberFormatException();
                }
                if (choice == -1) {
                    return false;
                }
                ticketRules.remove(choice);
                return true;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid choice! Try again! -1 to %d\n", ticketRules.size()-1);
            }
        } while (true);
    }

    public boolean updateTicketPriceRules() {
        String choose;
        System.out.println("=====================================");
        System.out.println("TICKET RULE UPDATE SCREEN");
        System.out.println("=====================================");

        ArrayList<TicketRule> ticketRules = ticketPriceManager.getTicketRules();
        System.out.println("Input -1 to Cancel!");
        for (int i = 0; i < ticketRules.size(); i++) {
            System.out.printf("%d) %s\n", i, ticketRules.get(i));
        }
        do {
            choose = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choose);
                if ( !(choice >= -1 && choice < ticketRules.size()) ) {
                    throw new NumberFormatException();
                }
                if (choice == -1) {
                    return false;
                }
                // UPDATE
                TicketRule temp = TicketRule.copyTicketRule(ticketRules.get(choice));
                TicketRule rule = ticketRules.get(choice);
                ticketRules.remove(choice);
                boolean result = updateTicketPriceRule(rule);
                if (!result)
                    ticketRules.add(choice, temp);
                return result;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid choice! Try again! -1 to %d\n", ticketRules.size()-1);
            }
        } while (true);
    }

    private boolean updateTicketPriceRule(TicketRule ticketRule) {
        addTicketTypeRule(ticketRule);
        addDaysOfWeekRule(ticketRule);
        addTimingRule(ticketRule);
        addPublicHolidayRule(ticketRule);
        setRulePrice(ticketRule);
        return insertTicketRule(ticketRule);
    }
}
