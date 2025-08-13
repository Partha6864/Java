package Bank_Account_Simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, Account> accounts;
    private Map<String, String> accountHolders;
    private Random random;

    public Bank() {
        accounts = new HashMap<>();
        accountHolders = new HashMap<>();
        random = new Random();
    }

    public String generateAccountNumber() {
        String accountNumber;
        do {
            long num = 10000000000L + (long) (random.nextDouble() * 90000000000L);
            accountNumber = String.valueOf(num);
        } while (accounts.containsKey(accountNumber) || accountNumber.length() != 11);
        return accountNumber;
    }

    public Account createAccount(String accountHolderName, String password) {
        if (accountHolders.containsKey(accountHolderName.toLowerCase())) {
            return null;
        }

        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(accountNumber, accountHolderName, password);
        accounts.put(accountNumber, newAccount);
        accountHolders.put(accountHolderName.toLowerCase(), accountNumber);
        return newAccount;
    }

    public Account login(String accountNumber, String password) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.verifyPassword(password)) {
            return account;
        }
        return null;
    }

    public Account loginByName(String accountHolderName, String password) {
        String accountNumber = accountHolders.get(accountHolderName.toLowerCase());
        if (accountNumber != null) {
            Account account = accounts.get(accountNumber);
            if (account != null && account.verifyPassword(password)) {
                return account;
            }
        }
        return null;
    }
}