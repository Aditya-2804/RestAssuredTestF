package Payloads;

import java.util.List;

public class Carts {


    private int id;

    private int userId;

    private List<Products> products;

    private String date;

    private int __v;

    public Carts() {
    }

    public Carts(int id, int userId, List<Products> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public Carts(int id, int userId, List<Products> products, String date, int __v) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.date = date;
        this.__v=__v;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", userId=" + userId +
                ", products=" + products +
                ", date='" + date + '\'' +
                '}';
    }
}
