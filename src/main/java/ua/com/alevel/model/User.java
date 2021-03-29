package ua.com.alevel.model;

public class User {
    Integer userId;
    String name;
    String lastName;
    String address;
    String mail;

    public Integer getUserId() {
        return userId;
    }


    public String getMail() {
        return mail;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", last name='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }


    public User(Integer user_id, String name, String lastName, String address, String mail) {
        this.userId = user_id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.mail = mail;
    }
}
