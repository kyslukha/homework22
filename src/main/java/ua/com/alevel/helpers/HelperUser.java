package ua.com.alevel.helpers;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;


public class HelperUser {
    public static String suggestUserMethod () {
        System.out.println("Here you can Create, Read, Update and Delete Users or press Back and back to menu");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }

    public static void methodsUser(String method, Connection connection) {
        if (method.equals("Read")) {
            ShowList(UserDao.getAllUsers(connection));
            methodsUser(suggestUserMethod(), connection);
        } else if (method.equals("Update")) {
            Integer userId = createUserId();
            String name = createName();
            String lastName = createLastName();
            String address = createAddress();
            String mail = createMail();
            UserDao.updateUser(connection, name, lastName,address, mail,userId);
            methodsUser(suggestUserMethod(), connection);
        } else if (method.equals("Create")) {
            String name = createName();
            String lastName = createLastName();
            String address = createAddress();
            String mail = createMail();
            UserDao.createUser(connection, name,lastName,address,mail);
            methodsUser(suggestUserMethod(), connection);
        } else if (method.equals("Delete")) {
            Integer user_id = createUserId();
            UserDao.deleteUser(connection, user_id);
            methodsUser(suggestUserMethod(), connection);
        }else if(method.equals("Back")){
            HelperApp.methodsApp(HelperApp.appSuggestion(),connection);
        }
    }

    private static void ShowList(List<User> list) {
        for(User user : list){
            System.out.println(user + "\n");
        }
    }

    private static String createMail() {
        System.out.println("Enter User mail");
        Scanner scanner = new Scanner(System.in);
        String mail = scanner.nextLine();
        return mail;
    }


    private static String createAddress() {
        System.out.println("Enter User address");
        Scanner scanner = new Scanner(System.in);
        String address = scanner.nextLine();
        return address;
    }

    private static String createLastName() {
        System.out.println("Enter User last name");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        return lastName;
    }

    private static Integer createUserId() {
        System.out.println("Enter user id");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        return id;
    }

    private static String createName() {
        System.out.println("Enter User name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return name;
    }


}
