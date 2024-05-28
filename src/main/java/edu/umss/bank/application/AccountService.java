package edu.umss.bank.application;

public interface AccountService {
    void createAccount(Account account);
    Account getAccount(String accountNumber);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
}
