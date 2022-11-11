package boundary.staffUI;

import controller.HolidayManager;
import controller.Initialization;
import controller.TicketPriceManager;
import entity.ticket.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class SystemSettingUI {

	private HolidayManager holidayManager;
	private TicketPriceManager ticketPriceManager;

	private Scanner sc;

	public SystemSettingUI() {
		this.holidayManager = Initialization.getINSTANCE().getHolidayManager();
		this.ticketPriceManager = Initialization.getINSTANCE().getTicketPriceManager();
		sc = new Scanner(System.in);
	}

	public  void configureTicketPrice()
	{
		//update ticket price
		String choose;
		do
		{
			System.out.println("How would you like to configure holiday");
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
					break;
				case "3":
					//updateTicketPrice
					break;
			}
		} while (!choose.equals("0"));
	}

	private void addTicketTypeRule(TicketRule ticketRule) {
		String choose = "-1";
		System.out.println("Ticket Type: ");
		System.out.println("Press 0 for None");
		System.out.println("Press 1 for Elderly");
		System.out.println("Press 2 for Student");

		do {
			choose = sc.nextLine();
			switch (choose) {
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
		}while (!choose.equals("0"));
	}

	private void addDaysOfWeekRule(TicketRule ticketRule) {
		EnumSet<DayOfWeek> daysOfWeek = EnumSet.noneOf(DayOfWeek.class);
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
			choose = sc.nextLine();
			switch (choose) {
				case "0":
					ticketRule.addRule(new DateRule(daysOfWeek));
					return;
				case "1":
					daysOfWeek.add(DayOfWeek.MONDAY);
					break;
				case "2":
					daysOfWeek.add(DayOfWeek.TUESDAY);
					break;
				case "3":
					daysOfWeek.add(DayOfWeek.WEDNESDAY);
					break;
				case "4":
					daysOfWeek.add(DayOfWeek.THURSDAY);
					break;
				case "5":
					daysOfWeek.add(DayOfWeek.FRIDAY);
					break;
				case "6":
					daysOfWeek.add(DayOfWeek.SATURDAY);
					break;
				case "7":
					daysOfWeek.add(DayOfWeek.SUNDAY);
					break;
				default:
					System.out.println("Invalid option! Try again!");
					break;
			}
			System.out.println("Updated Ticket Price Rules : " + daysOfWeek);
		} while (!choose.equals("0"));
	}

	private void addTimingRule(TicketRule ticketRule) {
		String choose;
		System.out.println("Press 0 for None");
		System.out.println("Press 1 for Before 6pm");
		System.out.println("Press 2 for After 6pm");
		do {
			choose = sc.nextLine();
			switch (choose) {
				case "0":
					break;
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
		} while (!choose.equals("0"));
	}

	private void addTicketPriceRules() {
		System.out.println("=====================================");
		System.out.println("ADD TICKET PRICE SCREEN");
		System.out.println("=====================================");
		TicketRule ticketRule = new TicketRule(10);

		addTicketTypeRule(ticketRule);
		addDaysOfWeekRule(ticketRule);
		addTimingRule(ticketRule);
	}

	public  void configureHoliday()
	{
		Scanner sc = new Scanner(System.in);
		int choose;
		do
		 {
			 System.out.println("How would you like to configure holiday");
			 System.out.println("1) Add holiday: ");
		     System.out.println("2) Delete holiday:");
		     System.out.println("3) Update holiday:");
			 System.out.println("0) Exit:");
			 choose = sc.nextInt();
			 switch(choose)
			 {
				 case 0:
					 return;
			     case 1:
			    	 //addholiday
			    	 break;
			     case 2:
					 printHolidayList();
			    	 //deleteholiday
			    	 break;
			     case 3:
					 printHolidayList();
			    	 //updateholiday
			    	 break;
			 }
		 }
		 while(choose != 0);
	}

	public void printHolidayList() {
		ArrayList<LocalDate> localDates = holidayManager.getLocalDateTimeArrayList();
		for (int i = 0; i < localDates.size(); i++) {
			System.out.printf("%d) %s\n", i, localDates.get(i));
		}
	}
}
