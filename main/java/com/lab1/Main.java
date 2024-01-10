import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int year;

    public Book(String title, String author, String isbn, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                '}';
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added to the library: " + book.getTitle());
    }

    public void showAllBooks() {
        System.out.println("All books in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void deleteBookByIsbn(String isbn) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getIsbn().equals(isbn)) {
                iterator.remove();
                System.out.println("Book with ISBN " + isbn + " has been removed from the library.");
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found in the library.");
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Java Programming", "John Doe", "1234567890", 2022);
        Book book2 = new Book("Python Basics", "Jane Smith", "0987654321", 2023);
        library.addBook(book1);
        library.addBook(book2);

        library.showAllBooks();

        String searchTitle = "Java Programming";
        Book foundBook = library.findBookByTitle(searchTitle);
        if (foundBook != null) {
            System.out.println("Book found by title '" + searchTitle + "': " + foundBook);
        } else {
            System.out.println("Book with title '" + searchTitle + "' not found in the library.");
        }

        String isbnToDelete = "1234567890";
        library.deleteBookByIsbn(isbnToDelete);

        library.showAllBooks();
    }
}
