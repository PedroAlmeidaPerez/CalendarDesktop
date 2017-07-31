package model;

import java.util.Calendar;

/**
 * Created by PedroAlmeida on 18.07.2017.
 */
public class MonthCalendarConfiguration extends CalendarConfiguration {
    public MonthCalendarConfiguration(Calendar calendar) {
        super(calendar);
        this.fieldOfCalendar = Calendar.DAY_OF_MONTH;
        this.valueForFieldOfCalendar = 1;
        this.numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
