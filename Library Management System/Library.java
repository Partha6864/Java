import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private int userCounter;
    private int bookCounter;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.userCounter = 1;
        this.bookCounter = 1;
    }

    public void addBook(String title, String author) {
        String bookId = String.format("BID%04d", bookCounter++);
        Book book = new Book(title, author, bookId);
        books.add(book);
        System.out.println("\nAdded book: " + book.getTitle() + " with ID: " + bookId + ".");
    }

    public void addUser(String name) {
        String userId = String.format("UID%04d", userCounter++);
        User user = new User(name, userId);
        users.add(user);
        System.out.println("\nAdded user: " + name + " with ID: " + userId + ".");
    }

    public void registerMember(String userId) {
        User user = findUser(userId);
        if (user == null) {
            System.out.println("\nError: User not found.");
            return;
        }
        if (user.isMember()) {
            System.out.println("\n" + user.getName() + " is already a member.");
            return;
        }
        user.setMember(true);
        System.out.println("\n" + user.getName() + " is now a library member.");
    }

    public void issueBook(String userId, String bookId) {
        User user = findUser(userId);
        if (user == null) {
            System.out.println("\nError: User not found.");
            return;
        }

        if (!user.isMember()) {
            System.out.println("\nError: " + user.getName() + " is not a library member.Please register first.");
            return;
        }

        Book book = findBook(bookId);
        if (book == null) {
            System.out.println("\nError: Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("\nError: '" + book.getTitle() + "' is not available.");
            return;
        }

        if (user.getBorrowedBooks().size() >= 3) {
            System.out.println("\nError: " + user.getName() + " has reached the borrowing limit(3 books).");
            return;
        }

        book.setAvailable(false);
        user.borrowBook(book);
        System.out.println("\nIssued '" + book.getTitle() + "' to " + user.getName() + ".");
    }

    public void returnBook(String userId, String isbn) {
        User user = findUser(userId);
        if (user == null) {
            System.out.println("\nError: User not found.");
            return;
        }

        Book book = findBook(isbn);
        if (book == null) {
            System.out.println("\nError: Book not found.");
            return;
        }

        if (!user.getBorrowedBooks().contains(book)) {
            System.out.println("\nError: " + user.getName() + " didn't borrow this book.");
            return;
        }

        book.setAvailable(true);
        user.returnBook(book);
        System.out.println("\nReturned '" + book.getTitle() + "' from " + user.getName());
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNo books in the library.");
            return;
        }
        System.out.println("\nLibrary Books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("\nNo users registered.");
            return;
        }
        System.out.println("\nAll Users:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }

    public void displayMembers() {
        List<User> members = new ArrayList<>();
        for (User user : users) {
            if (user.isMember()) {
                members.add(user);
            }
        }

        if (members.isEmpty()) {
            System.out.println("\nNo registered members.");
            return;
        }

        System.out.println("\nRegistered Members:");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i));
        }
    }

    private User findUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    private Book findBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
}
