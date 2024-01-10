import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Item {
    private String title;
    private String itemType;
    private String isbn;
    private boolean available;

    public Item(String title, String itemType, String isbn) {
        this.title = title;
        this.itemType = itemType;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getItemType() {
        return itemType;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", itemType='" + itemType + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                '}';
    }
}

class Client {
    private String name;
    private List<Item> borrowedItems;

    public Client(String name) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }

    public void borrowItem(Item item) {
        borrowedItems.add(item);
    }

    public void returnItem(Item item) {
        borrowedItems.remove(item);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", borrowedItems=" + borrowedItems +
                '}';
    }
}

class Library {
    private Map<String, Item> catalog;
    private List<Client> clients;

    public Library() {
        this.catalog = new HashMap<>();
        this.clients = new ArrayList<>();
    }

    public void addItem(Item item) {
        catalog.put(item.getIsbn(), item);
        System.out.println("Item added to the library catalog: " + item.getTitle());
    }

    public void removeItem(String isbn) {
        if (catalog.containsKey(isbn)) {
            Item removedItem = catalog.remove(isbn);
            System.out.println("Item with ISBN " + isbn + " has been removed from the library.");
            if (!removedItem.isAvailable()) {
                for (Client client : clients) {
                    for (Item borrowedItem : client.getBorrowedItems()) {
                        if (borrowedItem.getIsbn().equals(isbn)) {
                            client.returnItem(borrowedItem);
                        }
                    }
                }
            }
        } else {
            System.out.println("Item with ISBN " + isbn + " not found in the library catalog.");
        }
    }

    public void registerClient(Client client) {
        clients.add(client);
        System.out.println("Client registered: " + client.getName());
    }

    public void lendItem(String isbn, Client client) {
        if (catalog.containsKey(isbn)) {
            Item item = catalog.get(isbn);
            if (item.isAvailable()) {
                item.setAvailable(false);
                client.borrowItem(item);
                System.out.println("Item with ISBN " + isbn + " has been lent to " + client.getName());
            } else {
                System.out.println("Item with ISBN " + isbn + " is not available at the moment.");
            }
        } else {
            System.out.println("Item with ISBN " + isbn + " not found in the library catalog.");
        }
    }

    public void returnItem(String isbn, Client client) {
        for (Item borrowedItem : client.getBorrowedItems()) {
            if (borrowedItem.getIsbn().equals(isbn)) {
                borrowedItem.setAvailable(true);
                client.returnItem(borrowedItem);
                System.out.println("Item with ISBN " + isbn + " has been returned to the library by " + client.getName());
                return;
            }
        }
        System.out.println("Client " + client.getName() + " did not borrow item with ISBN " + isbn);
    }

    public void showAvailableItems() {
        System.out.println("Available items in the library:");
        for (Item item : catalog.values()) {
            if (item.isAvailable()) {
                System.out.println(item);
            }
        }
    }

    public void showBorrowedItems() {
        System.out.println("Borrowed items in the library:");
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Item book1 = new Item("Java Programming", "Book", "1234567890");
        Item dvd1 = new Item("Introduction to Algorithms DVD", "DVD", "0987654321");
        library.addItem(book1);
        library.addItem(dvd1);

        library.showAvailableItems();

        Client client1 = new Client("Alice");
        Client client2 = new Client("Bob");
        library.registerClient(client1);
        library.registerClient(client2);

        library.lendItem("1234567890", client1);
        library.lendItem("0987654321", client2);

        library.returnItem("1234567890", client1);

        library.showBorrowedItems();
    }
}
