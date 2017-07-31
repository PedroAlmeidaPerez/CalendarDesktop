package model;

import enums.ModeOfCalendar;

import java.util.ArrayList;
import java.util.Calendar;

import static enums.ModeOfCalendar.WEEK;

/**
 * Created by PedroAlmeida on 18.07.2017.
 */
public abstract class CalendarConfiguration {
    protected Calendar calendar;
    protected ArrayList<DayModel> days;
    protected int fieldOfCalendar;
    protected int valueForFieldOfCalendar;
    protected int numberOfDays;


    public CalendarConfiguration(Calendar calendar) {
        this.calendar = calendar;
    }

    public ArrayList<DayModel> getDays() {
        return days;
    }

    public void generateDays() {
        days = new ArrayList<DayModel>();
        Calendar c = (Calendar) calendar.clone();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        populateDays();
    }

    protected void populateDays() {
        calendar.set(fieldOfCalendar, valueForFieldOfCalendar);
        for (int i = 0; i < numberOfDays; i++) {
            days.add(new DayModel(calendar.getTime()));
            calendar.set(fieldOfCalendar, calendar.get(fieldOfCalendar) + 1);
        }
    }
}


