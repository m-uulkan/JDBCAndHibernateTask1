package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

    public class Main {
    static Scanner sc = new Scanner(System.in);
    static UserService userService = new UserServiceImpl();


    public static void main(String[] args) throws Exception {


        while (true) {
            System.out.println();
            commands();
            int number = sc.nextInt();
            switch (number) {
                case 1 -> {
                    userService.createUsersTable();
                }
                case 2 -> {
                    userService.saveUser("Uulkan", "Mamatsakova", (byte) 28);
                    userService.saveUser("Aijamal", "Asangazieva", (byte) 26);
                    userService.saveUser("Nurisa", "Mamiraimova", (byte) 19);
                    userService.saveUser("Hadicha", "Zamirbekova", (byte) 19);
                }
                case 3 -> {
                    System.out.println(userService.getAllUsers());
                }
                case 4 -> {
                    System.out.println("Write id");
                    int id = sc.nextInt();
                    userService.removeUserById(id);
                }
                case 5 -> {
                    userService.dropUsersTable();
                }
                case 6 -> {
                    userService.cleanUsersTable();
                }
                default -> throw new Exception("Ввели неправильную команду");
            }
        }
    }


    static void commands(){
        System.out.println("Press 1 to create table");
        System.out.println("Press 2 to save user");
        System.out.println("Press 3 to get all users");
        System.out.println("Press 4 to remove user by id");
        System.out.println("Press 5 to drop table");
        System.out.println("Press 6 to clean table");
    }

}