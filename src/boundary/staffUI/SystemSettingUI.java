package boundary.staffUI;

import controller.HolidayManager;
import controller.Initialization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemSettingUI {

	private HolidayManager holidayManager;
	public SystemSettingUI() {
		this.holidayManager = Initialization.getINSTANCE().getHolidayManager();
	}

	public  void configureTicketPrice()
	{
		//update ticket price 
		Scanner sc = new Scanner(System.in);
		int choose;
		do
		{
			System.out.println("How would you like to configure holiday");
			System.out.println("1) Add ticket price: ");
			System.out.println("2) Delete ticket price:");
			System.out.println("3) Update ticket price:");
			System.out.println("0) Exit:");
			choose = sc.nextInt();
			switch(choose)
			{
				case 0:
					return;
				case 1:
					//addTicketPrice
					break;
				case 2:
					//deleteTicketPrice
					break;
				case 3:
					//updateTicketPrice
					break;
			}
		}
		while(choose != 0);
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
