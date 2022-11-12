package controller;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeSet;

public class HolidayManager implements Serializable {
    TreeSet<LocalDate> localDateTimeArrayList;

    public HolidayManager() {
        localDateTimeArrayList = new TreeSet<>();
    }

    public TreeSet<LocalDate> getLocalDates() {
        return localDateTimeArrayList;
    }

    private void add(LocalDate localDate) {
        localDateTimeArrayList.add(localDate);
    }

    /**
     * Adds the Date into the list of Holidays
     * Date shall be a string in be in the format yyyyMMdd
     * @param date
     */
    public void addDate(String date) {
        localDateTimeArrayList.add(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public void addDate(LocalDate date) {
        localDateTimeArrayList.add(date);
    }

    public void removeDate(int index) {
        localDateTimeArrayList.remove(index);
    }
}
