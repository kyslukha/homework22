package ua.com.alevel.dao;

import ua.com.alevel.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public static void createCategory(Connection connection, String name) {
        String sqlInsertion = "INSERT INTO category (categoryName) VALUES (?)";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlInsertion);
                statement.setString(1, name);
                int rows = statement.executeUpdate();

                if (rows > 0)
                    System.out.println("A new Category has been inserted successfully!");
                else
                    System.out.println("Something went wrong with creation!");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }



    public static List<Category> getAllCategories(Connection connection) {
        String sqlSelectAll = "SELECT * FROM  category";
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectAll);
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt(1),resultSet.getString(2));
                categories.add(category);
            }


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return categories;
    }

    public static List<Integer> getCategoryByName(Connection connection, String name) {
        String sqlSelectByName = "SELECT Id FROM  category WHERE categoryName = ?";
        List<Integer> ids = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sqlSelectByName);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("Id");
                ids.add(id);
            }


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return ids;
    }


    public static void updateCategory(Connection connection, Integer id, String name) {
        String sqlUpdate = "UPDATE category  SET categoryName = ? WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlUpdate);
                statement.setString(1,name);
                statement.setInt(2, id);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("A new Category name with id"+ id + " has been updated!");
                else
                    System.out.println("Something went wrong with updating!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static void deleteCategory(Connection connection, Integer id) {
        String sqlDelete = "DELETE FROM category  WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlDelete);
                statement.setInt(1, id);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("A Category with id: "+ id + " has been deleted!");
                else
                    System.out.println("Something went wrong with deleting!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }



}
