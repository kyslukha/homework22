package ua.com.alevel.helpers;

import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.model.Category;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;


public class HelperCategory {

    public static String suggestCategoryMethod() {
        System.out.println("Here you can Create, Read, Update and Delete Categories or press Back for back the menu");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }

    public static void methodsCategory(String method, Connection connection){
        if(method.equals("Read")){
            getCategories(connection);
            methodsCategory(suggestCategoryMethod(),connection);
        }else if(method.equals("Update")){
            System.out.println("Enter the id to update Category.");
            Integer categoryId = getCategoryId();
            System.out.println("Enter a new name of the Category.");
            String categoryName = getCategoryName();
            CategoryDao.updateCategory(connection, categoryId, categoryName);
            methodsCategory(suggestCategoryMethod(),connection);
        }else if(method.equals("Create")){
            System.out.println("Enter a new name of the Category.");
            String categoryName = getCategoryName();
            insertCategory(connection, categoryName);
            methodsCategory(suggestCategoryMethod(),connection);
        }else if(method.equals("Delete")){
            System.out.println("Enter the id to delete Category.");
            Integer categoryId = getCategoryId();
            CategoryDao.deleteCategory(connection,categoryId);
            methodsCategory(suggestCategoryMethod(),connection);
        }else if(method.equals("Back")){
         HelperApp.methodsApp(HelperApp.appSuggestion(),connection);
        }
    }

    public static void insertCategory(Connection connection, String categoryName) {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.createCategory(connection, categoryName);
    }

    public static void getCategories(Connection connection) {
        List<Category> categories = CategoryDao.getAllCategories(connection);
        showCategories(categories);
    }

    private static void showCategories(List<Category> categories) {
        for(Category category : categories){
            System.out.println(category + "\n");
        }
    }

    public static String getCategoryName() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return name;
    }
    public static Integer getCategoryId() {
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        return id;
    }

}
