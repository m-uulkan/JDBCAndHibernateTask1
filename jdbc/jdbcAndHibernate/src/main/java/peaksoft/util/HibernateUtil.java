package peaksoft.util;


import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import peaksoft.model.User;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperty("connection.driver_class", "com.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/school")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "12345")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl_auto", "update")
                .buildSessionFactory();
        return sessionFactory;
    }

}
