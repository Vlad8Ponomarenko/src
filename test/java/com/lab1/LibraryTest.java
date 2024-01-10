import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setup() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Test Book", "Test Author", "0987654321", 2024);
        library.addBook(book);
        Assertions.assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testFindBookByTitle() {
        Book book1 = new Book("Test Book 1", "Author 1", "1111111111", 2021);
        Book book2 = new Book("Test Book 2", "Author 2", "2222222222", 2022);
        library.addBook(book1);
        library.addBook(book2);

        Book foundBook = library.findBookByTitle("Test Book 2");
        Assertions.assertEquals(book2, foundBook);
    }

    @Test
    public void testDeleteBookByIsbn() {
        Book book = new Book("Test Book", "Test Author", "0987654321", 2024);
        library.addBook(book);
        library.deleteBookByIsbn("0987654321");
        Assertions.assertEquals(0, library.getBooks().size());
    }
}
