package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Type;
import utils.HibernateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by PedroAlmeida on 05.07.2017.
 */
@Entity
public class DayModel implements Serializable {

    @Id
    @Type(type = "date")
    private Date date;
    private String note;

    public DayModel(Date date) {
        this.date = date;
    }

    public DayModel() {
    }

    public void saveNote(String note) {
        setNote(note);
        HibernateUtil.saveToDatabase(this);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
}
