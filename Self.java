import java.util.Arrays;
import java.util.List;

class Shelf {
    private String name;
    private List<Book> books;

    public Shelf(String name, Book... books) {
        this.name = name;
        this.books = Arrays.asList(books);
    }

    public String getName() { return name; }
    public List<Book> getBooks() { return books; }
}
