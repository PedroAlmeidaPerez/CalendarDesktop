package controller;


import enums.ModeOfCalendar;
import model.CalendarConfiguration;
import model.DayModel;
import utils.CalendarConfigurationFactory;
import utils.CalendarUtils;
import utils.HibernateUtil;
import view.CalendarView;
import view.NotesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static enums.ModeOfCalendar.WEEK;

/**
 * Created by PedroAlmeida on 04.07.2017.
 */

public class CalendarController {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private Calendar model;
    private CalendarView view;
    private ModeOfCalendar modeOfCalendar = WEEK;
    private int dayButtonStartPosition = 7;
    private CalendarConfiguration calendarConfiguration;
    private JButton selectedButton;


    public CalendarController(Calendar model, CalendarView view) {
        this.model = model;
        this.view = view;
        generateCalendarConfiguration();
        setUpViewEvents();
        this.view.updateView(new SimpleDateFormat(DD_MM_YYYY).format(model.getTime()), calendarConfiguration.getDays());
    }


    private void setUpViewEvents() {
        callNoteViews();
        addListenerToMoveButtons(view.getBackButton(), -7, -1);
        addListenerToMoveButtons(view.getForwardButton(), 7, 1);
        addModeOfCalendarListener();
        setDay();
    }


    private void addListenerToMoveButtons(JButton button, int amountOfDaysForMoving, int amountOfMonthsForMoving) {
        button.addActionListener(e -> {
                    switch (modeOfCalendar) {
                        case WEEK:
                            model.add(Calendar.DATE, amountOfDaysForMoving);
                            break;
                        case MONTH:
                            model.add(Calendar.MONTH, amountOfMonthsForMoving);
                            break;
                    }
                    generateCalendarConfiguration();
                    view.updateView(new SimpleDateFormat(DD_MM_YYYY).format(model.getTime()), calendarConfiguration.getDays());
                }
        );
    }

    private void setDay() {
        view.getFormattedTextField1().addActionListener((ActionEvent e) -> {

                    String txt = view.getFormattedTextField1().getText();
                    DateFormat df = new SimpleDateFormat(DD_MM_YYYY);
                    df.setLenient(false);
                    Date searchingDate;
                    try {
                        searchingDate = df.parse(txt);
                        Calendar c = Calendar.getInstance();
                        c.setLenient(false);
                        c.setTime(searchingDate);
                        model.setTime(c.getTime());
                        generateCalendarConfiguration();
                        view.updateView(txt, calendarConfiguration.getDays());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }


                }
        );
    }


    private DayModel getDayModelFromCalendarDays(int n) {
        return calendarConfiguration.getDays().get(n);
    }

    private void generateCalendarConfiguration() {
        this.calendarConfiguration = CalendarConfigurationFactory.configurationFor((Calendar) model.clone(), this.modeOfCalendar);
        calendarConfiguration.generateDays();

    }

    private void addModeOfCalendarListener() {
        view.getModeComboBox().addActionListener(e -> {
                    modeOfCalendar = (ModeOfCalendar) view.getModeComboBox().getSelectedItem();
                    generateCalendarConfiguration();
                    view.updateView(new SimpleDateFormat(DD_MM_YYYY).format(model.getTime()), calendarConfiguration.getDays());
                }
        );
    }

    private void callNoteViews() {
        for (int i = dayButtonStartPosition; i < view.getSeparateDayPanel().getComponentCount(); i++) {
            addListenerToDayButton(i);
        }
    }

    private void addListenerToDayButton(int number) {
        JButton dayButton = (JButton) view.getSeparateDayPanel().getComponent(number);
        dayButton.addActionListener(e -> {
            int numberOfDay = getDayNumberFromComponentNumber(e);
            DayModel selectedDay = getDayModelFromCalendarDays(numberOfDay);
            setNoteForSelectedDay(selectedDay);

        });
    }

    private int getDayNumberFromComponentNumber(ActionEvent e) {
        int indOfFirstDay = CalendarUtils.dayOfWeek(calendarConfiguration.getDays().get(0));
        int numberOfDay = view.getSeparateDayPanel().getComponentZOrder((Component) e.getSource()) - dayButtonStartPosition - indOfFirstDay;
        selectedButton = (JButton) e.getSource();
        return numberOfDay;
    }

    private void setNoteForSelectedDay(DayModel currentDay) {
        NotesView notesView = new NotesView();
        NotesController notesController = new NotesController(notesView, currentDay, selectedButton);
        String note = HibernateUtil.getNoteFromDatabase(currentDay);
        if (note != "") {
            notesView.getEnterYourNoteTextPane().setText(note);
        }

    }

}





