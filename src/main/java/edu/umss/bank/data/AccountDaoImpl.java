package edu.umss.bank.data;
import edu.umss.bank.application.Account;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class AccountDaoImpl implements AccountDao {
    private static AccountDaoImpl instance;
    private Map<String, Account> accounts = new HashMap<>();

    private AccountDaoImpl() {}

    public static AccountDaoImpl getInstance() {
        if (instance == null) {
            instance = new AccountDaoImpl();
        }
        return instance;
    }

    @Override
    public void createAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public void updateAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
}
