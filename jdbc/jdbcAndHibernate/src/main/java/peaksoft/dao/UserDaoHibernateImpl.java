package peaksoft.dao;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
           Util util=new Util();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Session session =HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("create  table if not exists users ( " +
                    " id serial not null ," +
                    " name  varchar(50) not null, " +
                    " last_name varchar(50) not null ," +
                    " age int  not null )").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Added successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("Drop TABLE if exists users").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Deleted table successfully");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user=new User(name,lastName,age);
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("Saved successfully: "+user);

    }

    @Override
    public void removeUserById(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user= (User) session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("Deleted successfully");
    }



    @Override
    public List<User> getAllUsers() {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User>userList=session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        System.out.println("Finded "+userList.size());
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        Session session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete from User ").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table is clean");
    }
}
