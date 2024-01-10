import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddItem() {
        Library library = new Library();
        Item item = new Item("Java Programming", "Book", "1234567890");
        library.addItem(item);
        assertTrue(library.checkAvailability("1234567890", 1));
    }

    @Test
    public void testRemoveItem() {
        Library library = new Library();
        Item item = new Item("Java Programming", "Book", "1234567890");
        library.addItem(item);
        library.removeItem("1234567890");
        assertFalse(library.checkAvailability("1234567890", 1)); 
    }

    @Test
    public void testRegisterClient() {
        Library library = new Library();
        Client client = new Client("Alice");
        library.registerClient(client);
        assertEquals(1, library.getClients().size()); 
    }


}

public class ClientTest {

    @Test
    public void testBorrowItem() {
        Library library = new Library();
        Item item = new Item("Java Programming", "Book", "1234567890");
        library.addItem(item);
        Client client = new Client("Alice");
        library.registerClient(client);
        library.lendItem("1234567890", client);
        assertEquals(1, client.getBorrowedItems().size()); 
    }

    @Test
    public void testReturnItem() {
        Library library = new Library();
        Item item = new Item("Java Programming", "Book", "1234567890");
        library.addItem(item);
        Client client = new Client("Alice");
        library.registerClient(client);
        library.lendItem("1234567890", client);
        library.returnItem("1234567890", client);
        assertEquals(0, client.getBorrowedItems().size()); 
    }

}
