package ecommerce;

import java.util.List;
import ecommerce.Product;
import ecommerce.User;
import ecommerce.Order;
import ecommerce.ECommercePlatform;


public class ECommerceDemo {
    public static void main(String[] args) {
        ECommercePlatform platform = new ECommercePlatform();

        User user1 = new User(1, "JohnDoe");
        User user2 = new User(2, "AliceSmith");
        platform.addUser(user1);
        platform.addUser(user2);

        Product product1 = new Product(101, "Phone", 499.99, 50);
        Product product2 = new Product(102, "Laptop", 899.99, 30);
        platform.addProduct(product1);
        platform.addProduct(product2);

        user1.addToCart(product1, 2);
        user2.addToCart(product2, 1);
        user2.addToCart(product1, 1);

        Order order1 = new Order(201, user1.getId());
        order1.addProduct(product1, 2);
        order1.calculateTotalPrice();
        platform.createOrder(order1);

        Order order2 = new Order(202, user2.getId());
        order2.addProduct(product2, 1);
        order2.addProduct(product1, 1);
        order2.calculateTotalPrice();
        platform.createOrder(order2);

        List<Product> recommendationsUser1 = platform.recommendProductsToUser(user1);
        List<Product> recommendationsUser2 = platform.recommendProductsToUser(user2);

        //platform.updateStock(product1.getId(), 48);
        //platform.updateStock(product2.getId(), 29);

        System.out.println("Final state of users:");
        platform.listUsers();

        System.out.println("\nFinal state of products:");
        platform.listProducts();

        System.out.println("\nFinal state of orders:");
        platform.listOrders();

        System.out.println("\nRecommendations for User 1:");
        for (Product recommendedProduct : recommendationsUser1) {
            System.out.println(recommendedProduct);
        }

        System.out.println("\nRecommendations for User 2:");
        for (Product recommendedProduct : recommendationsUser2) {
            System.out.println(recommendedProduct);
        }
    }
}
