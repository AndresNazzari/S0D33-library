package core.domain.entities;

public class Lend extends Base {

    private Book book;
    private User user;
    private String lendDate;
    private String dueDate;
    private String returnDate;


    public Lend() {
    }

    public Lend(int id, Book book, User user, String lendDate, String dueDate, String returnDate) {
        super(id);
        setBook(book);
        setUser(user);
        setLendDate(lendDate);
        setDueDate(dueDate);
        setReturnDate(returnDate);
    }

    public Lend(Book book, User user, String lendDate, String dueDate, String returnDate) {
        setBook(book);
        setUser(user);
        setLendDate(lendDate);
        setDueDate(dueDate);
        setReturnDate(returnDate);
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
