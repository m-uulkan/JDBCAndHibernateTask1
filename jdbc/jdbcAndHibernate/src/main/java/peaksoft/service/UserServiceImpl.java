package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {
      UserDao dao = new UserDaoJdbcImpl();
      List<User>userList=new ArrayList<>();
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
        dao.getAllUsers();
        return userList;
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}
