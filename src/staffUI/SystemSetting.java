package Movie;

import java.util.Scanner;

public class SystemSetting {
	
	public  void configureticketprice()
	{
		//update ticket price 
		
	}
	public  void configureholiday()
	{
		Scanner sc = new Scanner(System.in);
		int choose;
		do
		 {
			 System.out.println("How would you like to configure holiday");
			 System.out.println("1) Add holiday: ");
		     System.out.println("2) delete holiday:");
		     System.out.println("3) Update holiday:");
			 choose = sc.nextInt();
			 switch(choose)
			 {
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
		 while(choose < 4);
	}
	public void addholiday()
	{
		
	}
	public void deleteholiday()
	{
		
	}
	public void updateholiday()
	{
		
	}

}
