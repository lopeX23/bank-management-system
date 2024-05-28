package edu.umss.bank.application;
import edu.umss.bank.data.AccountDao;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void createAccount(Account account) {
        accountDao.createAccount(account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountDao.getAccount(accountNumber);
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            accountDao.updateAccount(account);
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = accountDao.getAccount(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountDao.updateAccount(account);
        }
    }
}
