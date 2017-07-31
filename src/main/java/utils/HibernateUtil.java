package utils;


import model.DayModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by PedroAlmeida on 18.07.2017.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static void saveToDatabase(Object obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(obj);
        session.getTransaction().commit();
        session.close();
    }

    public static String getNoteFromDatabase(DayModel day) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DayModel selectedday = session.get(DayModel.class, day.getDate());
        session.getTransaction().commit();
        session.close();
        if (selectedday != null) {
            return selectedday.getNote();
        } else {
            return "";
        }
    }
}
