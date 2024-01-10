import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ECommerceSystemTest {

    @Test
    public void testAddToCart() {
        Product mockProduct = mock(Product.class);
        Cart cart = new Cart();
        
        cart.addToCart(mockProduct, 3);

        assertEquals(3, cart.getCartItems().get(mockProduct).intValue());
    }

    @Test
    public void testRemoveFromCart() {
        Product mockProduct = mock(Product.class);
        Cart cart = new Cart();
        cart.addToCart(mockProduct, 5);

        cart.removeFromCart(mockProduct, 2);

        assertEquals(3, cart.getCartItems().get(mockProduct).intValue());
    }

    @Test
    public void testOrderStatusUpdate() {
        Product product1 = new Product(1, "Product 1", 10.0);
        Map<Product, Integer> orderProducts = new HashMap<>();
        orderProducts.put(product1, 2);

        Order order = new Order(orderProducts);

        Order mockOrder = mock(Order.class);
        when(mockOrder.getStatus()).thenReturn("Processing");

        mockOrder.setStatus("Shipped");

        assertEquals("Shipped", mockOrder.getStatus());
    }
}
