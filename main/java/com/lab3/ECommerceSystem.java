import java.util.*;

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Cart {
    private Map<Product, Integer> cartItems;

    public Cart() {
        this.cartItems = new HashMap<>();
    }

    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product, int quantity) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            if (currentQuantity <= quantity) {
                cartItems.remove(product);
            } else {
                cartItems.put(product, currentQuantity - quantity);
            }
        }
    }

    public void clearCart() {
        cartItems.clear();
    }
}

class Order {
    private static int orderCounter = 0;

    private int orderId;
    private Map<Product, Integer> products;
    private String status;

    public Order(Map<Product, Integer> products) {
        this.orderId = ++orderCounter;
        this.products = new HashMap<>(products);
        this.status = "Processing";
    }

    public int getOrderId() {
        return orderId;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

public class ECommerceSystem {
    public static void main(String[] args) {
        Product product1 = new Product(1, "Product 1", 10.0);
        Product product2 = new Product(2, "Product 2", 15.0);

        Cart cart = new Cart();
        cart.addToCart(product1, 2);
        cart.addToCart(product2, 3);

        Map<Product, Integer> cartItems = cart.getCartItems();
        System.out.println("Items in Cart:");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            System.out.println(entry.getKey().getName() + " - Quantity: " + entry.getValue());
        }

        cart.removeFromCart(product1, 1);
        System.out.println("\nCart after removing one item from Product 1:");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            System.out.println(entry.getKey().getName() + " - Quantity: " + entry.getValue());
        }

        cart.clearCart();
        System.out.println("\nCart after clearing:");
        System.out.println(cart.getCartItems());

        Map<Product, Integer> orderProducts = new HashMap<>();
        orderProducts.put(product1, 2);
        orderProducts.put(product2, 1);

        Order order = new Order(orderProducts);
        System.out.println("\nOrder Details:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Products: " + order.getProducts());
        System.out.println("Status: " + order.getStatus());

        order.setStatus("Shipped");
        System.out.println("\nUpdated Order Status: " + order.getStatus());
    }
}
