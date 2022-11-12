package boundary.staffUI;

import com.sun.source.tree.Tree;
import controller.HolidayManager;
import controller.Initialization;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeSet;

public class HolidayConfigurationUI {

    private Scanner sc;
    private HolidayManager holidayManager;

    public HolidayConfigurationUI() {
        this.holidayManager = Initialization.getINSTANCE().getHolidayManager();
        sc = new Scanner(System.in);
    }

    public void configure()
    {
        String choose;
        do
        {
            System.out.println("How would you like to configure holiday");
            System.out.println("1) Add holiday");
            System.out.println("2) Delete holiday");
            System.out.println("0) Exit");
            choose = sc.nextLine();
            switch(choose)
            {
                case "0":
                    return;
                case "1":
                    //addholiday
                    addHoliday();
                    break;
                case "2":
                    //deleteholiday
                    deleteHoliday();
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
        while(true);
    }

    public void addHoliday() {
        String date;
        LocalDate localDate;

        System.out.println("=====================================");
        System.out.println("ADD HOLIDAY SCREEN");
        System.out.println("=====================================");

        do {
            System.out.println("To cancel, input: 0");
            System.out.printf("Please input a date in this Format(yyyyMMdd)\n:");
            date = sc.nextLine();
            if (date.toLowerCase(Locale.ROOT).equals("0")) {
                return;
            }
            try{
                localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
                holidayManager.addDate(localDate);
                return;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input/date! Check your format/date!");
            }
        } while (true);
    }

    public boolean deleteHoliday() {
        String choose;
        System.out.println("=====================================");
        System.out.println("DELETE HOLIDAY SCREEN");
        System.out.println("=====================================");
        System.out.println("-1) Cancel");

        TreeSet<LocalDate> localDates = holidayManager.getLocalDates();
        int i = 0;
        for (LocalDate localDate : localDates) {
            System.out.printf("%d) %s\n", i++, localDate.toString());
        }

        do {
            System.out.printf("Select a date to remove : ");
            choose = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choose);
                if ( !(choice >= -1 && choice < localDates.size()) ) {
                    throw new NumberFormatException();
                }
                if (choice == -1) {
                    return false;
                }
                i = 0;
                for (LocalDate localDate : localDates) {
                    if (i++ == choice) {
                        localDates.remove(localDate);
                        break;
                    }
                }
                return true;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid choice! Try again! -1 to %d\n", localDates.size()-1);
            }
        } while (true);
    }
}
