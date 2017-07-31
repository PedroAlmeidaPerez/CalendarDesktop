package controller;

import model.DayModel;
import utils.HibernateUtil;
import view.NotesView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by PedroAlmeida on 07/07/2017.
 */
public class NotesController {

    private NotesView view;
    private DayModel dayModel;
    private JButton selectedButton;

    public NotesController(NotesView view, DayModel dayModel, JButton selectedButton) {
        this.view = view;
        this.dayModel = dayModel;
        this.selectedButton = selectedButton;
        setUpViewEvents();
    }

    private void setUpViewEvents() {
        addingCancelButtonListener();
        addingSaveButtonListener();
    }

    private void addingCancelButtonListener() {
        view.getCancelButton().addActionListener(e -> view.getNoteFrame().dispose());
    }

    private void addingSaveButtonListener() {
        view.getSave().addActionListener(e -> {
            dayModel.saveNote(view.getEnterYourNoteTextPane().getText());
            selectedButton.setForeground(Color.RED);
            view.getNoteFrame().dispose();
        });
    }

}