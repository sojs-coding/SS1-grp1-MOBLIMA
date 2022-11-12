package boundary.staffUI;

import controller.HolidayManager;
import controller.Initialization;
import controller.TicketPriceManager;
import entity.cinema.CinemaClass;
import entity.ticket.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * Class to configure the system settings
 */
public class SystemSettingUI {
	private Scanner sc;
    /**
	 * Constructor for the SystemSettingUi class
	 */
	public SystemSettingUI() {
		sc = new Scanner(System.in);
	}
    /**
	 * Method to display options for configuring system settings
	 */
	public void configure() {
		System.out.println("How would you like to configure System settings ");
		System.out.println("|(1) Configure holidays |");
		System.out.println("|(2) Configure ticket prices |");
		System.out.println("=============================");
		String c2;
		c2 = sc.nextLine();
		switch(c2)
		{
			case "1":
				configureHoliday();
				break;
			case "2":
				configureTicketPrice();
				break;
		}
	}
    /**
	 * method for configuring the holiday
	 */
	public void configureHoliday() {
		HolidayConfigurationUI holidayConfigurationUI = new HolidayConfigurationUI();
		holidayConfigurationUI.configure();
	}
    /**
	 * method for configuring the ticket price
	 */
	public void configureTicketPrice()
	{
		TicketConfigurationUI ticketConfigurationUI = new TicketConfigurationUI();
		ticketConfigurationUI.configure();
	}
}
