package ua.com.alevel.helpers;

import ua.com.alevel.dao.OrderDao;
import ua.com.alevel.model.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class HelperOrder {

    public static String suggestOrderMethod () {
        System.out.println("Here you can Create, Read, Update and Delete Orders or press Back and back to menu");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }

    public static void methodsOrders(String method, Connection connection) {
        if (method.equals("Read")) {
            ShowList(OrderDao.getAllOrders(connection));
            methodsOrders(suggestOrderMethod(), connection);
        } else if (method.equals("Update")) {
            String orderStatus = getOrderStatus();
            Integer order_id = getOrderId();
            OrderDao.updateOrderStatus(connection, orderStatus, order_id);
            methodsOrders(suggestOrderMethod(), connection);
        } else if (method.equals("Create")) {
            String productName = getProductName();
            String email =  getMail();
            String orderStatus = getStatusName();
            OrderDao.createOrder(connection, productName, email, orderStatus);
            methodsOrders(suggestOrderMethod(), connection);
        } else if (method.equals("Delete")) {
            Integer order_id = getOrderId();
            OrderDao.deleteOrder(connection, order_id);
            methodsOrders(suggestOrderMethod(), connection);
        }else if(method.equals("Back")){
            HelperApp.methodsApp(HelperApp.appSuggestion(),connection);
        }
    }

    private static String getStatusName() {
        System.out.println("Enter order status to order");
        Scanner scanner = new Scanner(System.in);
        String status = scanner.nextLine();
        return status;
    }

    private static String getMail() {
        System.out.println("Enter Your Email to order");
        Scanner scanner = new Scanner(System.in);
        String mail = scanner.nextLine();
        return mail;
    }

    private static String getProductName() {
        System.out.println("Enter Product name to order");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return name;
    }

    private static Integer getOrderId() {
        System.out.println("Enter Order_id");
        Scanner scanner = new Scanner(System.in);
        Integer orderId = scanner.nextInt();
        return orderId;
    }

    private static String getOrderStatus() {
        System.out.println("Enter Order status");
        Scanner scanner = new Scanner(System.in);
        String status = scanner.nextLine();
        return status;
    }

    private static void ShowList(List<Order> allOrders) {
        for(Order order : allOrders){
            System.out.println(order + "\n");
        }
    }


}
