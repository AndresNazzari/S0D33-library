package infrastructure.seeders;

import core.application.services.PasswordService;
import core.domain.entities.Book;
import core.domain.entities.User;
import infrastructure.repositories.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Seeders {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LendRepository lendRepository;

    public Seeders() {
        bookRepository = new BookRepository();
        userRepository = new UserRepository();
        lendRepository = new LendRepository();
    }

    public void seedBooks() throws SQLException {
        List<Book> books = Arrays.asList(
                new Book("1984", 1949, "Dystopian novel by George Orwell.", "George Orwell", "Dystopian", "2021-01-01", "2021-01-01", null),
                new Book("Animal Farm", 1945, "Political fable by George Orwell.", "George Orwell", "Political Satire", "2021-01-01", "2021-01-01", null),
                new Book("Brave New World", 1932, "Dystopian novel by Aldous Huxley.", "Aldous Huxley", "Dystopian", "2021-01-01", "2021-01-01", null),
                new Book("The Doors of Perception", 1954, "Philosophical essay by Aldous Huxley.", "Aldous Huxley", "Philosophy", "2021-01-01", "2021-01-01", null),
                new Book("The Hobbit", 1937, "Children's fantasy novel by J.R.R. Tolkien.", "J.R.R. Tolkien", "Fantasy", "2021-01-01", "2021-01-01", null),
                new Book("The Lord of the Rings", 1954, "Epic high fantasy novel by J.R.R. Tolkien.", "J.R.R. Tolkien", "Fantasy", "2021-01-01", "2021-01-01", null),
                new Book("The Silmarillion", 1977, "Collection of mythopoeic works by J.R.R. Tolkien.", "J.R.R. Tolkien", "Fantasy", "2021-01-01", "2021-01-01", null),
                new Book("Pride and Prejudice", 1813, "Romantic novel by Jane Austen.", "Jane Austen", "Romance", "2021-01-01", "2021-01-01", null),
                new Book("Emma", 1815, "Novel by Jane Austen.", "Jane Austen", "Romance", "2021-01-01", "2021-01-01", null),
                new Book("Moby Dick", 1851, "Novel by Herman Melville.", "Herman Melville", "Adventure", "2021-01-01", "2021-01-01", null),
                new Book("Billy Budd, Sailor", 1924, "Posthumous novel by Herman Melville.", "Herman Melville", "Tragedy", "2021-01-01", "2021-01-01", null),
                new Book("War and Peace", 1869, "Novel by Leo Tolstoy.", "Leo Tolstoy", "Historical Fiction", "2021-01-01", "2021-01-01", null),
                new Book("Anna Karenina", 1877, "Novel by Leo Tolstoy.", "Leo Tolstoy", "Historical Fiction", "2021-01-01", "2021-01-01", null),
                new Book("The Brothers Karamazov", 1880, "Novel by Fyodor Dostoevsky.", "Fyodor Dostoevsky", "Philosophical Fiction", "2021-01-01", "2021-01-01", null),
                new Book("Crime and Punishment", 1866, "Novel by Fyodor Dostoevsky.", "Fyodor Dostoevsky", "Psychological Fiction", "2021-01-01", "2021-01-01", null),
                new Book("The Catcher in the Rye", 1951, "Novel by J.D. Salinger.", "J.D. Salinger", "Literature", "2021-01-01", "2021-01-01", null),
                new Book("The Great Gatsby", 1925, "Novel by F. Scott Fitzgerald.", "F. Scott Fitzgerald", "Literature", "2021-01-01", "2021-01-01", null),
                new Book("Frankenstein", 1818, "Gothic novel by Mary Shelley.", "Mary Shelley", "Horror", "2021-01-01", "2021-01-01", null),
                new Book("Dracula", 1897, "Gothic horror novel by Bram Stoker.", "Bram Stoker", "Horror", "2021-01-01", "2021-01-01", null),
                new Book("The Picture of Dorian Gray", 1890, "Philosophical novel by Oscar Wilde.", "Oscar Wilde", "Philosophical Fiction", "2021-01-01", "2021-01-01", null),
                new Book("Jane Eyre", 1847, "Novel by Charlotte Brontë.", "Charlotte Brontë", "Literature", "2021-01-01", "2021-01-01", null),
                new Book("Wuthering Heights", 1847, "Novel by Emily Brontë.", "Emily Brontë", "Gothic Fiction", "2021-01-01", "2021-01-01", null),
                new Book("Les Misérables", 1862, "Novel by Victor Hugo.", "Victor Hugo", "Historical Fiction", "2021-01-01", "2021-01-01", null),
                new Book("Hamlet", 1609, "Tragedy by William Shakespeare.", "William Shakespeare", "Drama", "2021-01-01", "2021-01-01", null),
                new Book("Macbeth", 1623, "Tragedy by William Shakespeare.", "William Shakespeare", "Drama", "2021-01-01", "2021-01-01", null),
                new Book("The Merchant of Venice", 1600, "Comedy by William Shakespeare.", "William Shakespeare", "Drama", "2021-01-01", "2021-01-01", null),
                new Book("A Midsummer Night's Dream", 1605, "Comedy by William Shakespeare.", "William Shakespeare", "Comedy", "2021-01-01", "2021-01-01", null),
                new Book("Othello", 1603, "Tragedy by William Shakespeare.", "William Shakespeare", "Tragedy", "2021-01-01", "2021-01-01", null),
                new Book("King Lear", 1606, "Tragedy by William Shakespeare.", "William Shakespeare", "Tragedy", "2021-01-01", "2021-01-01", null),
                new Book("Romeo and Juliet", 1597, "Tragedy by William Shakespeare.", "William Shakespeare", "Drama", "2021-01-01", "2021-01-01", null)
        );


        for (Book book : books) {
            bookRepository.create(book);
        }

        System.out.println("Books seeded");
        System.out.println("-------------------------");
    }

    public void seedUsers() throws SQLException {
        PasswordService passwordService = new PasswordService();
        String pass = passwordService.hashPassword("123456");

        List<User> users = Arrays.asList(
                new User("John", "Doe", "jd123456", pass, 1),
                new User("Jane", "Doe", "janedoe", null, 0),
                new User("Alice", "Walker", "alicewalker", null, 0),
                new User("Bob", "Smith", "bobsmith", null, 0),
                new User("Charlie", "Brown", "charliebrown", null, 0),
                new User("David", "Wilson", "davidwilson", null, 0),
                new User("Eve", "Polastri", "evepolastri", null, 0),
                new User("Frank", "Hart", "frankhart", null, 0),
                new User("Grace", "Adams", "graceadams", pass, 1),
                new User("Henry", "Long", "henrylong", null, 0)
        );

        for (User user : users) {
            userRepository.create(user);
        }

        System.out.println("Users seeded");
        System.out.println("-------------------------");
    }

    public static void seedDatabase() throws SQLException {
        Seeders seeders = new Seeders();
        seeders.seedBooks();
        seeders.seedUsers();
    }
}
