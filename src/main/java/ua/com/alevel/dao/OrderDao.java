package ua.com.alevel.dao;

import ua.com.alevel.model.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public static void createOrder(Connection connection, String productName, String mail, String order_status) {
        String sqlInsertion = "INSERT INTO orders (productId, userId, orderDate, status ) VALUES (?,?,?,?)";
        PreparedStatement statement;
        Integer product_id = ProductDao.getProductIdByName(connection,productName);
        Integer user_id = UserDao.getUserIdByMail(connection,mail);
        LocalDate date = LocalDate.now();
        {
            try {
                statement = connection.prepareStatement(sqlInsertion);
                statement.setInt(1, product_id);
                statement.setInt(2, user_id);
                statement.setDate(3, Date.valueOf(date));
                statement.setString(4, order_status);
                int rows = statement.executeUpdate();

                if (rows > 0)
                    System.out.println("A new Order has been created successfully!");
                else
                    System.out.println("Something went wrong with creation!");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static List<Order> getAllOrders(Connection connection) {
        String sqlSelectAll = "SELECT * FROM  orders";
        List<Order> orders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectAll);
            while (resultSet.next()) {
                Order order = new Order(resultSet.getInt(5),resultSet.getInt(1),
                        resultSet.getInt(2), resultSet.getDate(3), resultSet.getString(4));
                orders.add(order);
            }


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return orders;
    }

    public static void updateOrderStatus(Connection connection,  String orderStatus, Integer idOrder) {
        String sqlUpdate = "UPDATE orders SET status= ? WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlUpdate);
                statement.setString(1,orderStatus);
                statement.setInt(2, idOrder);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("Order with id: "+ idOrder + " has been updated!");
                else
                    System.out.println("Something went wrong with updating!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static void deleteOrder(Connection connection, Integer id) {
        String sqlDelete = "DELETE FROM orders  WHERE Id = ?";
        PreparedStatement statement;
        {
            try {
                statement = connection.prepareStatement(sqlDelete);
                statement.setInt(1, id);
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("An Order with id: "+ id + " has been deleted!");
                else
                    System.out.println("Something went wrong with deleting!");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
