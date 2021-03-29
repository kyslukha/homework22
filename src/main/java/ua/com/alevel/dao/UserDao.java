package ua.com.alevel.dao;

import ua.com.alevel.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static void createUser(Connection connection, String name, String lastName, String address,  String mail) {
        String sqlInsertion = "INSERT INTO user (name, lastName, address, mail ) VALUES (?,?,?,?)";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlInsertion);
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setString(3, address);
                statement.setString(4, mail);
                int rows = statement.executeUpdate();

                if (rows > 0)
                    System.out.println("A new User has been inserted successfully!");
                else
                    System.out.println("Something went wrong with creation!");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static void updateUser(Connection connection, String name,
                                      String lastName, String address,
                                      String mail,
                                      Integer Id                      ) {
        String sqlUpdate = "UPDATE user  SET name = ?, lastName = ?, address = ?," +
                " mail = ?  WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlUpdate);
                statement.setString(1,name);
                statement.setString(2, lastName);
                statement.setString(3, address);
                statement.setString(4, mail);
                statement.setInt(5, Id);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("A new User name with id"+ Id + "has been updated!");
                else
                    System.out.println("Something went wrong with updating!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static List<User> getAllUsers(Connection connection) {
        String sqlSelectAll = "SELECT * FROM  user";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectAll);
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(5),
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                users.add(user);
            }


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return users;
    }

    public static Integer getUserIdByMail(Connection connection, String mail){
        Integer id= null;
        List<User> users = getAllUsers(connection);
        for (User user : users){
            if(mail.equals(user.getMail())){
                id = user.getUserId();
            }
        }
        return id;
    }

    public static void deleteUser(Connection connection, Integer id) {
        String sqlDelete = "DELETE FROM user  WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlDelete);
                statement.setInt(1, id);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("User with id: "+ id + " has been deleted!");
                else
                    System.out.println("Something went wrong with deleting!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
