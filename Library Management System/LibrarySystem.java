import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Library Management System!");

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Register Member");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display All Books");
            System.out.println("7. Display All Users");
            System.out.println("8. Display All Members");
            System.out.println("9. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;

                case 2:
                    System.out.print("\nEnter user name: ");
                    String name = scanner.nextLine();
                    library.addUser(name);
                    break;

                case 3:
                    System.out.print("\nEnter user ID to register as member: ");
                    String regUserId = scanner.nextLine();
                    library.registerMember(regUserId);
                    break;

                case 4:
                    System.out.print("\nEnter user ID: ");
                    String issueUserId = scanner.nextLine();
                    System.out.print("Enter book ID: ");
                    String issueBookId = scanner.nextLine();
                    library.issueBook(issueUserId, issueBookId);
                    break;

                case 5:
                    System.out.print("\nEnter user ID: ");
                    String returnUserId = scanner.nextLine();
                    System.out.print("Enter book ID: ");
                    String returnBookId = scanner.nextLine();
                    library.returnBook(returnUserId, returnBookId);
                    break;

                case 6:
                    library.displayBooks();
                    break;

                case 7:
                    library.displayUsers();
                    break;

                case 8:
                    library.displayMembers();
                    break;

                case 9:
                    System.out.println("\nThank you for using the Library System.Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("\nInvalid choice.Please try again.");
            }
        }
    }
}
