import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<Author> authors = Arrays.asList(
            new Author("Leo Tolstoy"),
            new Author("Fyodor Dostoevsky"),
            new Author("Alexander Pushkin"),
            new Author("Anton Chekhov"),
            new Author("Nikolai Gogol"),
            new Author("Ivan Turgenev"),
            new Author("Mikhail Bulgakov"),
            new Author("J.R.R. Tolkien"),
            new Author("H.P. Lovecraft"),
            new Author("Isaac Asimov"),
            new Author("C.S. Lewis"),
            new Author("J.K. Rowling"),
            new Author("George Orwell"),
            new Author("H.G. Wells")
        );

        List<Book> books = Arrays.asList(
            new Book("War and Peace", authors.get(0), "https://www.gutenberg.org/cache/epub/2600/pg2600-images.html"),
            new Book("Crime and Punishment", authors.get(1), "https://www.gutenberg.org/files/2554/2554-h/2554-h.htm"),
            new Book("Eugene Onegin", authors.get(2), "https://www.gutenberg.org/files/23997/23997-h/23997-h.htm"),
            new Book("The Man in a Case", authors.get(3), "hhttps://web.seducoahuila.gob.mx/biblioweb/upload/THE%20MAN%20IN%20A%20CASE.pdf"),
            new Book("Dead Souls", authors.get(4), "https://www.gutenberg.org/files/1081/1081-h/1081-h.htm"),
            new Book("Fathers and Sons", authors.get(5), "https://www.gutenberg.org/files/47935/47935-h/47935-h.htm"),
            new Book("The Master and Margarita", authors.get(6), "https://www.masterandmargarita.eu/estore/pdf/eben001_mastermargarita_glenny.pdf"),
            new Book("The Lord of the Rings", authors.get(7), "https://gosafir.com/mag/wp-content/uploads/2019/12/Tolkien-J.-The-lord-of-the-rings-HarperCollins-ebooks-2010.pdf"),
            new Book("The Hobbit", authors.get(7), "https://primarysite-prod-sorted.s3.amazonaws.com/gobowen-primary-school/UploadedDocument/db3a2424ab834ade94f16606d5567712/the-hobbit-1.pdf"),
            new Book("The Call of Cthulhu", authors.get(8), "https://www.hplovecraft.com/writings/texts/fiction/cc.aspx"),
            new Book("Foundation", authors.get(9), "https://www.ycn.com.au/asimovfoundation/images/d.pdf"),
            new Book("The Chronicles of Narnia", authors.get(10), "https://archive.org/details/LewisCSNarnia3TheHorseAndHisBoy/Lewis_C_S_-_Narnia_1_-_The_Magician_s_Nephew/"),
            new Book("Harry Potter", authors.get(11), "https://kvongcmehsanalibrary.wordpress.com/wp-content/uploads/2021/07/harrypotter.pdf"),
            new Book("1984", authors.get(12), "https://www.philosophia.cl/biblioteca/orwell/1984.pdf"),
            new Book("The Time Machine", authors.get(13), "https://www.fourmilab.ch/etexts/www/wells/timemach/timemach.pdf")
        );

        Shelf classicsShelf = new Shelf("Russian Classics", books.get(0), books.get(1), books.get(5));
        Shelf poetryShelf = new Shelf("Russian Poetry", books.get(2));
        Shelf dramaShelf = new Shelf("Russian Drama", books.get(3), books.get(4));
        Shelf modernShelf = new Shelf("Soviet Literature", books.get(6));
        Shelf fantasyShelf = new Shelf("Fantasy Shelf", books.get(7), books.get(8), books.get(12), books.get(13));
        Shelf horrorShelf = new Shelf("Horror Shelf", books.get(9));
        Shelf sciFiShelf = new Shelf("Science Fiction Shelf", books.get(10), books.get(14));
        Shelf dystopianShelf = new Shelf("Dystopian Shelf", books.get(11));

        Section classicsSection = new Section("Classic Literature", classicsShelf);
        Section poetrySection = new Section("Poetry", poetryShelf);
        Section dramaSection = new Section("Drama", dramaShelf);
        Section modernSection = new Section("Soviet and Modern Literature", modernShelf);
        Section fantasySection = new Section("Fantasy", fantasyShelf);
        Section horrorSection = new Section("Horror", horrorShelf);
        Section sciFiSection = new Section("Science Fiction", sciFiShelf);
        Section dystopianSection = new Section("Dystopian", dystopianShelf);

        Library library = new Library(classicsSection, poetrySection, dramaSection, modernSection, fantasySection, horrorSection, sciFiSection, dystopianSection);

        while (true) {
            System.out.println("\n=== Library ===");
            System.out.println("1. View Sections");
            System.out.println("2. View Authors");
            System.out.println("3. View Books by Author");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInt(scanner);
            if (choice == 0) break;
            if (choice == 2) {
                showAuthors(authors);
                continue;
            }
            if (choice == 1) {
                showSections(scanner, library);
            }
            if (choice == 3) {
                showBooksByAuthor(scanner, authors, books);
            }
        }
        scanner.close();
    }

    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Invalid input. Please enter a number.");
        }
        return scanner.nextInt();
    }

    private static void showAuthors(List<Author> authors) {
        System.out.println("\n=== Authors ===");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println((i + 1) + ". " + authors.get(i).getName());
        }
    }

    private static void showSections(Scanner scanner, Library library) {
        while (true) {
            System.out.println("\n=== Sections ===");
            List<Section> sections = library.getSections();
            for (int i = 0; i < sections.size(); i++) {
                System.out.println((i + 1) + ". " + sections.get(i).getName());
            }
            System.out.println("0. Go Back");
            System.out.print("Choose a section: ");

            int sectionChoice = getValidInt(scanner);
            if (sectionChoice == 0) break;

            Section chosenSection = sections.get(sectionChoice - 1);
            showShelves(scanner, chosenSection);
        }
    }

    private static void showShelves(Scanner scanner, Section section) {
        while (true) {
            System.out.println("\n=== " + section.getName() + " ===");
            List<Shelf> shelves = section.getShelves();
            for (int i = 0; i < shelves.size(); i++) {
                System.out.println((i + 1) + ". " + shelves.get(i).getName());
            }
            System.out.println("0. Go Back");
            System.out.print("Choose a shelf: ");

            int shelfChoice = getValidInt(scanner);
            if (shelfChoice == 0) break;

            Shelf chosenShelf = shelves.get(shelfChoice - 1);
            showBooks(scanner, chosenShelf.getBooks());
        }
    }

    private static void showBooksByAuthor(Scanner scanner, List<Author> authors, List<Book> books) {
        System.out.println("\n=== Choose an Author ===");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println((i + 1) + ". " + authors.get(i).getName());
        }
        System.out.println("0. Go Back");
        System.out.print("Choose an author: ");

        int authorChoice = getValidInt(scanner);
        if (authorChoice == 0) return;

        Author chosenAuthor = authors.get(authorChoice - 1);
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(chosenAuthor)) {
                filteredBooks.add(book);
            }
        }

        if (filteredBooks.isEmpty()) {
            System.out.println("No books found for this author.");
        } else {
            showBooks(scanner, filteredBooks);
        }
    }

    private static void showBooks(Scanner scanner, List<Book> books) {
        while (true) {
            System.out.println("\n=== Books ===");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i).getTitle());
            }
            System.out.println("0. Go Back");
            System.out.print("Choose a book to read: ");

            int bookChoice = getValidInt(scanner);
            if (bookChoice == 0) break;

            Book chosenBook = books.get(bookChoice - 1);
            openBook(chosenBook);
        }
    }

    private static void openBook(Book book) {
        try {
            Desktop.getDesktop().browse(new URI(book.getLink()));
        } catch (IOException | java.net.URISyntaxException e) {
            System.out.println("Unable to open the link.");
        }
    }
}
