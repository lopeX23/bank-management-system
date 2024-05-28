package edu.umss.bank.presentation;
import edu.umss.bank.application.Account;
import edu.umss.bank.application.AccountService;
import java.util.Scanner;

public class BankUI {
    private AccountService accountService;
    private Scanner scanner = new Scanner(System.in);

    public BankUI(AccountService accountService) {
        this.accountService = accountService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

    private void createAccount() {
        System.out.println("Enter Account Number:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter Owner Name:");
        String owner = scanner.nextLine();
        System.out.println("Enter Initial Balance:");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(accountNumber, owner, balance);
        accountService.createAccount(account);
        System.out.println("Account created successfully.");
    }

    private void deposit() {
        System.out.println("Enter Account Number:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter Amount to Deposit:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        accountService.deposit(accountNumber, amount);
        System.out.println("Deposit successful.");
    }

    private void withdraw() {
        System.out.println("Enter Account Number:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter Amount to Withdraw:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        accountService.withdraw(accountNumber, amount);
        System.out.println("Withdrawal successful.");
    }

    private void checkBalance() {
        System.out.println("Enter Account Number:");
        String accountNumber = scanner.nextLine();

        Account account = accountService.getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}