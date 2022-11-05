package boundary.staffUI;

import java.util.Scanner;

public class SystemSettingUI {
	
	public  void configureTicketPrice()
	{
		//update ticket price 
		
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
			    	 //deleteholiday
			    	 break;
			     case 3:
			    	 //updateholiday
			    	 break;
			 }
		 }
		 while(choose != 0);
	}
}
