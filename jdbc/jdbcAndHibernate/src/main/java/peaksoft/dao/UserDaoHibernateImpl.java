package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
   // UserDao userDao=new UserDaoHibernateImpl();
    HibernateUtil hbutil=new HibernateUtil();
    User user=new User();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session=HibernateUtil.getSession().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("Added successfully : "+user);
    }

    @Override
    public void dropUsersTable() {
        Session session=HibernateUtil.getSession().openSession();
        session.beginTransaction();
        session.createQuery("drop table is exists users ").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Deleted table successfully");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user=new User();
        Session session=HibernateUtil.getSession().openSession();
        session.beginTransaction();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("Saved successfully: "+user);

    }

    @Override
    public void removeUserById(long id) {

        Session session = HibernateUtil.getSession().openSession();
        session.beginTransaction();
        User user= (User) session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("Deleted successfully");
    }



    @Override
    public List<User> getAllUsers() {
        Session session= HibernateUtil.getSession().openSession();
        session.beginTransaction();
        List<User>userList=session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        System.out.println("Finded "+userList.size());
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session=HibernateUtil.getSession().openSession();
        session.beginTransaction();
        session.createQuery("Delete from user").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table is clean");
    }
}
