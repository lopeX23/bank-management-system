package edu.umss.bank.data;
import edu.umss.bank.application.Account;
import java.util.List;

public interface AccountDao {
    void createAccount(Account account);
    Account getAccount(String accountNumber);
    void updateAccount(Account account);
    List<Account> getAllAccounts();
}
