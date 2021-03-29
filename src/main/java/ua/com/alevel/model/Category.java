package ua.com.alevel.model;

import java.util.Scanner;

public class Category {
    public Integer categoryId;
    public  String categoryName;



    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Integer getCategoryId() {
        return categoryId;
    }


    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

}
