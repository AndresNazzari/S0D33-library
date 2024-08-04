package core.domain.entities;

public class Book extends Base {
    private String title;
    private int publishYear;
    private String description;
    private String author;
    private String genre;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    public Book() {
    }

    public Book(int id, String title, int publishYear, String description, String author, String genre, String createdAt, String updatedAt, String deletedAt) {
        super(id);
        setTitle(title);
        setPublishYear(publishYear);
        setDescription(description);
        setAuthor(author);
        setGenre(genre);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
    }

    public Book(int id, String title, int publishYear, String description, String author, String genre) {
        super(id);
        setTitle(title);
        setPublishYear(publishYear);
        setDescription(description);
        setAuthor(author);
        setGenre(genre);
    }

    public Book(String title, int publishYear, String description, String author, String genre, String createdAt, String updatedAt, String deletedAt) {
        setTitle(title);
        setPublishYear(publishYear);
        setDescription(description);
        setAuthor(author);
        setGenre(genre);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
}
