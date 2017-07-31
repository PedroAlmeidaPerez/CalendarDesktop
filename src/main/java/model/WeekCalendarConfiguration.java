package model;

import java.util.Calendar;

/**
 * Created by PedroAlmeida on 18.07.2017.
 */
public class WeekCalendarConfiguration extends CalendarConfiguration {
    public WeekCalendarConfiguration(Calendar calendar) {
        super(calendar);
        this.fieldOfCalendar = Calendar.DAY_OF_WEEK;
        this.valueForFieldOfCalendar = Calendar.MONDAY;
        this.numberOfDays = 7;
    }
}
