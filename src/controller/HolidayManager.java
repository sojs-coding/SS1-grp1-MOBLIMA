package controller;

import java.time.LocalDate;
import java.util.ArrayList;

public class HolidayManager {
    ArrayList<LocalDate> localDateTimeArrayList;

    public HolidayManager() {
        localDateTimeArrayList = new ArrayList<>();
    }

    public ArrayList<LocalDate> getLocalDateTimeArrayList() {
        return localDateTimeArrayList;
    }

    public void addDate(LocalDate localDate) {
        localDateTimeArrayList.add(localDate);
    }

    public void removeDate(int index) {
        localDateTimeArrayList.remove(index);
    }
}
