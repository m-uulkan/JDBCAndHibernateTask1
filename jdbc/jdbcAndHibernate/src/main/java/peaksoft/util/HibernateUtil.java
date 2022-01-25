package peaksoft.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory session = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Session not created : " + ex);

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSession(){
        return session;
    }

    public static void shutDown(){
        getSession().close();
    }
}
