package peaksoft.dao;

import org.hibernate.Session;
import peaksoft.dao.UserDao;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = util.setUp().openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        System.out.println("creat table successfully");
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = util.setUp().openSession();
        session.beginTransaction();
        session.createSQLQuery("Drop TABLE users").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        Session session = util.setUp().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("Added user successfully");
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = util.setUp().openSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
        System.out.println("delete user in database successfully"+user);
        session.close();
    }



    @Override
    public List<User> getAllUsers() {
        Session session = util.setUp().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }


    @Override
    public void cleanUsersTable() {
        Session session = util.setUp().openSession();
        session.beginTransaction();
        session.createSQLQuery("delete from Users ").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("clean user successfully");

    }
}