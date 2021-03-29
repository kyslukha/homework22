package ua.com.alevel.dao;

import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDao {




    public static void createProduct(Connection connection, String name, Integer price, Integer categoryId){
        if (!ProductDao.checkCategory(CategoryDao.getAllCategories(connection),categoryId)) {
            String catName = getNewCategory();
            CategoryDao.createCategory(connection, catName);
            Integer categoryID = CategoryDao.getCategoryByName(connection, catName).get(0);
            createIfCategoryExists(connection,name,price,categoryID);
        }
        createIfCategoryExists(connection,name,price,categoryId);
    }


    public static boolean checkCategory(List<Category> categoryList, Integer categoryId) {
        boolean isExist = false;
        for(int i = 0; i < categoryList.size(); i++){
            if (categoryList.get(i).getCategoryId() != categoryId ){
            }else
            isExist = true;
        }
        return isExist;
    }

    public static void deleteProduct(Connection connection, Integer Id) {
        String sqlDelete = "DELETE FROM product  WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlDelete);
                statement.setInt(1, Id);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("A Product with id: "+ Id + " has been deleted!");
                else
                    System.out.println("Something went wrong with deleting!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }


    public static void updateProduct(Connection connection, String name, Integer price ,Integer categoryId, Integer Id) {
        String sqlUpdate = "UPDATE product  SET  nameProduct = ?, price = ?, categoryId = ? WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlUpdate);
                statement.setString(1,name);
                statement.setInt(2, price);
                statement.setInt(3, categoryId);
                statement.setInt(4, Id);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("Product name with id: "+ Id + " has been updated!");
                else
                    System.out.println("Something went wrong with updating!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }



    public static void createIfCategoryExists(Connection connection, String name, Integer price, Integer categoryId) {

        String sqlInsertion = "INSERT INTO product (nameProduct, price, categoryId) VALUES (?, ?, ?)";
        creatingMethod(connection, name, price, categoryId, sqlInsertion);
    }

    private static void creatingMethod(Connection connection, String nameProduct, Integer price, Integer categoryId, String sqlInsertion) {
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlInsertion);
                statement.setString(1, nameProduct);
                statement.setInt(2,price);
                statement.setInt(3,categoryId);

                int rows = statement.executeUpdate();

                if (rows > 0)
                    System.out.println("A new Product has been inserted successfully!");
                else
                    System.out.println("Something went wrong with creation!");
            } catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());;
            }
        }
    }



    private static List<Category> getCategoryList(Connection connection) {
        List<Category> categories = CategoryDao.getAllCategories(connection);
        return categories;
    }
    public static List<Product> getAllProducts(Connection connection) {
        String sqlSelectAll = "SELECT * FROM  product";
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectAll);
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt(4),resultSet.getString(1),resultSet.getInt(2),resultSet.getInt(3));
                products.add(product);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return products;
    }

    private static String getNewCategory() {
        System.out.println("To create product with new Category, enter the name of it. ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return name;
    }

    public static Integer getProductIdByName(Connection connection, String name){
        Integer id = null;
        List<Product> list = getAllProducts(connection);
        for(Product product : list){
            if(name.equals(product.getProductName())){
                id = product.getProductId();
            }
        }
        return id;
    }
}
