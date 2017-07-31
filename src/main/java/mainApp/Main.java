package mainApp;

import controller.CalendarController;
import view.CalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by PedroAlmeida on 04.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        CalendarView view = new CalendarView();
        Calendar model = new GregorianCalendar();
        CalendarController controller = new CalendarController(model, view);

    }
}

