package enums;

/**
 * Created by PedroAlmeida on 07.07.2017.
 */

public enum ModeOfCalendar {
    WEEK("Week"), MONTH("Month");
    private String mode;

    private ModeOfCalendar(String s) {
        mode = s;
    }

    public String toString() {
        return mode;
    }

}
