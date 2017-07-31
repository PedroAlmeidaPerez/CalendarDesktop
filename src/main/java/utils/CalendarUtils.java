package utils;

import model.DayModel;

import java.util.Calendar;

/**
 * Created by PedroAlmeida on 17.07.2017.
 */
public final class CalendarUtils {

    private CalendarUtils() {
        throw new AssertionError("Instantiating utility class.");
    }


    public static int dayOfWeek(DayModel day) {
        Calendar c = Calendar.getInstance();
        c.setTime(day.getDate());
        return convertDaysFromCalendarToIndexesInWeek(c.get(Calendar.DAY_OF_WEEK));

    }

    public static int dayOfMonth(DayModel day) {
        Calendar c = Calendar.getInstance();
        c.setTime(day.getDate());
        return c.get(Calendar.DAY_OF_MONTH);

    }

    public static int convertDaysFromCalendarToIndexesInWeek(int dayInCalendar) {
        switch (dayInCalendar) {
            case Calendar.MONDAY:
                return 0;
            case Calendar.TUESDAY:
                return 1;
            case Calendar.WEDNESDAY:
                return 2;
            case Calendar.THURSDAY:
                return 3;
            case Calendar.FRIDAY:
                return 4;
            case Calendar.SATURDAY:
                return 5;
            case Calendar.SUNDAY:
                return 6;
        }
        return 0;
    }

}
