package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {

     UserDao dao=new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        dao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name,lastName,age);

    }

    @Override
    public void removeUserById(long id) {
        dao.removeUserById(id);
    }



    public List<User> getAllUsers() {
      return   dao.getAllUsers();

    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}
