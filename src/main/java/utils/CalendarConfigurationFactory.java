package utils;

import enums.ModeOfCalendar;
import model.CalendarConfiguration;
import model.MonthCalendarConfiguration;
import model.WeekCalendarConfiguration;

import java.util.Calendar;

import static enums.ModeOfCalendar.WEEK;

/**
 * Created by PedroAlmeida on 18.07.2017.
 */
public final class CalendarConfigurationFactory {
    private CalendarConfigurationFactory() {
        throw new AssertionError("Instantiating utility class.");
    }

    public static CalendarConfiguration configurationFor(Calendar calendar, ModeOfCalendar modeOfCalendar) {
        if (modeOfCalendar == WEEK) {
            return new WeekCalendarConfiguration(calendar);
        } else {
            return new MonthCalendarConfiguration(calendar);
        }
    }
}
