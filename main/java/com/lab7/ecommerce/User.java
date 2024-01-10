package ecommerce;

import java.util.Map;
import java.util.HashMap;

public class User {
    private Integer id;
    private String username;
    private Map<Product, Integer> cart;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
        this.cart = new HashMap<>();
    }

    public void addToCart(Product product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        int currentQuantity = cart.getOrDefault(product, 0);
        if (currentQuantity > 0) {
            if (currentQuantity <= quantity) {
                cart.remove(product);
            } else {
                cart.put(product, currentQuantity - quantity);
            }
        }
    }
    

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    
}

