import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String userId;
    private boolean isMember;
    private List<Book> borrowedBooks;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.isMember = false;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isMember() {
        return isMember;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return name + " (ID: " + userId + ") - " +
                (isMember ? "Member" : "Non-Member") + " | Borrowed: " + borrowedBooks.size();
    }
}
