package Bank_Account_Simulation;

import java.util.Scanner;

public class BankAccountSimulation {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║      WELCOME TO THE BANKING      ║");
        System.out.println("╚══════════════════════════════════╝");

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Account Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAccountCreation(bank, scanner);
                    break;

                case 2:
                    handleLogin(bank, scanner);
                    break;

                case 3:
                    System.out.println("\nThank you for banking with us.Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice.Please try again.");
            }
        }
    }

    private static void handleAccountCreation(Bank bank, Scanner scanner) {
        System.out.print("\nEnter Full Name: ");
        String name = scanner.nextLine();

        if (bank.loginByName(name, "") != null) {
            System.out.println("\nAlready have an account.");
            System.out.println("Please login instead of creating a new account.");
            return;
        }

        System.out.print("Create Password: ");
        String password = scanner.nextLine();

        Account newAccount = bank.createAccount(name, password);
        if (newAccount == null) {
            System.out.println("\nAccount creation failed.Please try again.");
        } else {
            System.out.println("\nAccount created successfully!");
            System.out.println("┌─────────────────────────────────┐");
            System.out.println("│         ACCOUNT DETAILS         │");
            System.out.println("├─────────────────────────────────┤");
            System.out.printf("│ Account Holder: %-15s │\n", name);
            System.out.printf("│ Account Number: %-15s │\n", newAccount.getAccountNumber());
            System.out.println("└─────────────────────────────────┘");
            System.out.println("Please save account number and keep password secure!");
        }
    }

    private static void handleLogin(Bank bank, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Login Options ---");
            System.out.println("1. Login with A/C Number");
            System.out.println("2. Login with Name");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int loginChoice = scanner.nextInt();
            scanner.nextLine();

            if (loginChoice == 3) {
                return;
            }

            Account existingAccount = null;
            if (loginChoice == 1) {
                System.out.print("\nEnter 11-Digit A/C Number: ");
                String accNumber = scanner.nextLine();
                System.out.print("Enter Password: ");
                String accPassword = scanner.nextLine();
                existingAccount = bank.login(accNumber, accPassword);
            } else if (loginChoice == 2) {
                System.out.print("\nEnter Name: ");
                String loginName = scanner.nextLine();
                System.out.print("Enter Password: ");
                String namePassword = scanner.nextLine();
                existingAccount = bank.loginByName(loginName, namePassword);
            } else {
                System.out.println("Invalid choice.Please try again.");
                continue;
            }

            if (existingAccount == null) {
                System.out.println("\nLogin failed.Invalid credentials or account not found.");
                System.out.println("If you don't have an account,please create one.");
            } else {
                System.out.println("\nLogin successful! Welcome, " + existingAccount.getAccountHolderName() + "!");
                accountMenu(existingAccount, scanner);
                return;
            }
        }
    }

    private static void accountMenu(Account account, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Account Dashboard ---");
            System.out.println("Holder: " + account.getAccountHolderName());
            System.out.println("Account: " + account.getAccountNumber());
            System.out.println("----------------------------------------");
            System.out.println("1. Deposit Amount");
            System.out.println("2. Withdrawl Amount");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (depositAmount <= 0) {
                        System.out.println("Invalid amount.Deposit amount must be positive.");
                    } else {
                        account.deposit(depositAmount);
                        System.out.println("Deposited Successfully!");
                    }
                    break;

                case 2:
                    System.out.print("\nEnter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (withdrawAmount <= 0) {
                        System.out.println("Invalid amount.Withdrawal amount must be positive.");
                    } else {
                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();
                        if (account.withdraw(withdrawAmount, password)) {
                            System.out.println("Withdrawal Completed!");
                        } else {
                            System.out.println("Withdrawal failed.Incorrect password or insufficient funds.");
                        }
                    }
                    break;

                case 3:
                    System.out.printf("\nCurrent Balance: %.2f%n", account.getBalance());
                    break;

                case 4:
                    account.printTransactionHistory();
                    break;

                case 5:
                    System.out.print("\nEnter Current Password: ");
                    String currentPassword = scanner.nextLine();
                    if (!account.verifyPassword(currentPassword)) {
                        System.out.println("Incorrect password.Password change failed.");
                        break;
                    }
                    System.out.print("Enter New Password: ");
                    String newPassword = scanner.nextLine();
                    account.changePassword(newPassword);
                    System.out.println("Password changed successfully!");
                    break;

                case 6:
                    System.out.println("\nLogging out...");
                    System.out.println("Goodbye, " + account.getAccountHolderName() + "!");
                    return;

                default:
                    System.out.println("\nInvalid choice.Please try again.");
            }
        }
    }
}