package Bank_Account_Simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private String password;
    private double balance;
    private List<String> transactions;

    public Account(String accountNumber, String accountHolderName, String password) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.password = password;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        transactions.add("Account created.Initial balance: 0.00");
        transactions.add("Account Holder: " + accountHolderName);
    }

    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        recordTransaction("Password Changed", 0);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            transactions.add("Invalid deposit attempt: Amount must be positive.");
            return;
        }
        balance += amount;
        recordTransaction("Deposit", amount);
    }

    public boolean withdraw(double amount, String inputPassword) {
        if (!verifyPassword(inputPassword)) {
            transactions.add("Withdrawal failed: Incorrect password.");
            return false;
        }
        if (amount <= 0) {
            transactions.add("Invalid withdrawal attempt: Amount must be positive.");
            return false;
        }
        if (amount > balance) {
            transactions.add(String.format("Withdrawal failed: Insufficient funds.Requested: %.2f,Available: %.2f",
                    amount, balance));
            return false;
        }
        balance -= amount;
        recordTransaction("Withdrawal", amount);
        return true;
    }

    private void recordTransaction(String type, double amount) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String amountStr = (amount > 0) ? String.format("%.2f", amount) : "N/A";
        transactions.add(String.format("[%s] %-18s | Amount: %-10s | Balance: %.2f",
                timestamp, type, amountStr, balance));
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History for Account: " + accountNumber + " ---");
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Timestamp            | Type               | Amount      | Balance");
        System.out.println("----------------------------------------------------------------------------------------");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
}