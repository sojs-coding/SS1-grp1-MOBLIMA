package controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

/**
 Represents a Manager for all the Holidays
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class HolidayManager implements Serializable {
    /**
     * Represents the LocalDates for the Holidays
     */
    TreeSet<LocalDate> localDateArrayList;

    /**
     * Constructor for the HolidayManager
     * Create an ArrayList for the localDateArrayList
     */
    public HolidayManager() {
        localDateArrayList = new TreeSet<>();
    }

    /**
     * Get the ArrayList of the localDates
     * @return ArrayList of the localDates
     */
    public TreeSet<LocalDate> getLocalDates() {
        return localDateArrayList;
    }

    /**
     * Adds the Date into the list of Holidays
     * @param date Date shall be a string in be in the format yyyyMMdd
     */
    public void addDate(String date) {
        localDateArrayList.add(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    /**
     * Adds the Date into the list of Holidays
     * @param date LocalDate Object
     */
    public void addDate(LocalDate date) {
        localDateArrayList.add(date);
    }

    /**
     * Removes date based on the index it is in, in the localDateArrayList
     * @param index
     */
    public void removeDate(int index) {
        localDateArrayList.remove(index);
    }
}
