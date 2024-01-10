package ecommerce;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Integer id;
    private Integer userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Order(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
        this.orderDetails = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        orderDetails.put(product, orderDetails.getOrDefault(product, 0) + quantity);
    }

    public void calculateTotalPrice() {
        totalPrice = orderDetails.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<Product, Integer> getOrderDetails() {
        return orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Integer getId() {
        return id;
    }
}
