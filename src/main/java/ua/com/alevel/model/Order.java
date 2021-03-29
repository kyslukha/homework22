package ua.com.alevel.model;


import java.util.Date;

public class Order {
    public Integer orderId;
    public Integer productId;
    public Integer userId;
    public Date orderDate;
    public String orderStatus;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", product_id=" + productId +
                ", user_id=" + userId +
                ", order_date=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }


    public Order(Integer orderId, Integer productId, Integer userId, Date orderDate, String orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId= userId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

}

