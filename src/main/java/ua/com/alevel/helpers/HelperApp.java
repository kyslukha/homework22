package ua.com.alevel.helpers;

import ua.com.alevel.connection.DBConnector;

import java.sql.Connection;
import java.util.Scanner;

import static ua.com.alevel.helpers.HelperCategory.methodsCategory;
import static ua.com.alevel.helpers.HelperCategory.suggestCategoryMethod;
import static ua.com.alevel.helpers.HelperOrder.methodsOrders;
import static ua.com.alevel.helpers.HelperOrder.suggestOrderMethod;
import static ua.com.alevel.helpers.HelperProduct.methodsProduct;
import static ua.com.alevel.helpers.HelperProduct.suggestProductMethod;
import static ua.com.alevel.helpers.HelperUser.methodsUser;
import static ua.com.alevel.helpers.HelperUser.suggestUserMethod;

public class HelperApp {

    public static String appSuggestion () {
        System.out.println("Here you can manipulate with objects : Category, Products, User, Orders or you can press Exit and exit from the App");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }

    public static void methodsApp(String method, Connection connection) {
        if (method.equals("Category")) {
            methodsCategory(suggestCategoryMethod(),connection);
        } else if (method.equals("Products")) {
          methodsProduct(suggestProductMethod(),connection);
        } else if (method.equals("User")) {
            methodsUser((suggestUserMethod()),connection);
        } else if (method.equals("Orders")) {
            methodsOrders(suggestOrderMethod(),connection);
        } else if (method.equals("Exit")) {
            DBConnector.Close(connection);
            System.exit(0);
        }
    }



}
