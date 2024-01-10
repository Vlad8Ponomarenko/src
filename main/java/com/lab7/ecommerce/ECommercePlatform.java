package ecommerce;

import java.util.*;

import ecommerce.Product;
import ecommerce.User;
import ecommerce.Order;

public class ECommercePlatform {
    private Map<Integer, User> users;
    private Map<Integer, Product> products;
    private Map<Integer, Order> orders;

    public ECommercePlatform() {
        this.users = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void removeUser(Integer userId) {
        users.remove(userId);
    }

    public User getUser(Integer userId) {
        return users.get(userId);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void removeProduct(Integer productId) {
        products.remove(productId);
    }

    public Product getProduct(Integer productId) {
        return products.get(productId);
    }

    public void createOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void cancelOrder(Integer orderId) {
        orders.remove(orderId);
    }

    public Order getOrder(Integer orderId) {
        return orders.get(orderId);
    }

    public void listUsers() {
        for (User user : users.values()) {
            System.out.println(user.getId() + ": " + user.getUsername());
        }
    }
    
    public void listProducts() {
        for (Product product : products.values()) {
            System.out.println(product.getId() + ": " + product.getName());
        }
    }
    
    public void listOrders() {
        for (Order order : orders.values()) {
            System.out.println(order.getId() + ": " + order.getTotalPrice());
        }
    }
    

    public List<Product> recommendProductsToUser(User user) {
        return new ArrayList<>(); 
    }

}
