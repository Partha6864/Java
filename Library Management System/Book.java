public class Book {
    private String title;
    private String author;
    private String bookId;
    private boolean isAvailable;

    public Book(String title, String author, String bookId) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + " [ID: " + bookId + "] - "
                + (isAvailable ? "Available" : "Borrowed");
    }
}