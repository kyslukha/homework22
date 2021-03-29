package ua.com.alevel.model;

public class Product {
    private Integer productId;
    private String productName;
    private Integer price;
    private Integer categoryId;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }


    public Product(Integer productId, String productName, Integer price, Integer categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }
}
