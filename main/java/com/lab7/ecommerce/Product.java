package ecommerce;
import java.util.Comparator;
import java.util.List;

public class Product {
    private Integer id;
    private String name;
    private double price;
    private int stock;

    public Product(Integer id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }
 
     public static class NameComparator implements Comparator<Product> {
         @Override
         public int compare(Product p1, Product p2) {
             return p1.getName().compareTo(p2.getName());
         }
     }
 
     public static class StockComparator implements Comparator<Product> {
         @Override
         public int compare(Product p1, Product p2) {
             return Integer.compare(p1.getStock(), p2.getStock());
         }
     }
 }