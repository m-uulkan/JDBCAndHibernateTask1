package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    Util util = new Util();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connect = util.connect();
        Statement statement = connect.createStatement();
        //Query to create a table
        String query = "CREATE TABLE users("
                + "id serial primary key, "
                + "name VARCHAR (100) NOT NULL, "
                + "Lastname VARCHAR(100) NOT NULL, "
                + "age INT  NOT NULL); ";

        statement.executeUpdate(query);
        System.out.println("Table Created......");
    }
    public void dropUsersTable() {
        String SQL = "DROP TABLE  users";
        try (Connection connection = util.connect();
             Statement stmt = connection.createStatement()){
             stmt.executeUpdate(SQL);
            System.out.println("Table users is delete ");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String SQL="insert into users(name,lastname,age)values(?,?,?)";
        try(Connection connection= util.connect();
            PreparedStatement prtst= connection.prepareStatement(SQL)){
            prtst.setString(1,name);
            prtst.setString(2,lastName);
            prtst.setInt(3,age);
            prtst.executeUpdate();
            System.out.println(name+" user already saved");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void removeUserById(long id) {
        String SQL="delete from users where id =?";
        try(Connection connection=util.connect();
            PreparedStatement prst=connection.prepareStatement(SQL)) {
            prst.setLong(1,id);
            int rowDeleted= prst.executeUpdate();
            if(rowDeleted>0){
                System.out.println("user was deleted successfully");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public List<User> getAllUsers() {
        String SQL="select*from users";
        List<User>userList=new ArrayList<>();
        User user=new User();

        try (Connection connection= util.connect();
        Statement statement= connection.createStatement();
             ResultSet rset= statement.executeQuery(SQL))
        {
          while(rset.next()){
//             user.setId(rset.getLong("id"));
//             user.setName(rset.getString("name"));
//             user.setLastName(rset.getString("lastname"));
//             user.setAge((byte) rset.getInt("age"));
              System.out.println(rset.getInt("id")+" " +rset.getString("name")+
                     " "+ rset.getString("lastname")+" " +rset.getByte("age"));
              userList.add(user);
          }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String SQL="delete from users";
        try(Connection connection=util.connect();
        Statement statement= connection.createStatement())
        {
        statement.executeUpdate(SQL);
            System.out.println("Table is empty");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}