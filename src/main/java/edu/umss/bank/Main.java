package edu.umss.bank;

import edu.umss.bank.application.AccountService;
import edu.umss.bank.application.AccountServiceImpl;
import edu.umss.bank.data.AccountDao;
import edu.umss.bank.data.AccountDaoImpl;
import edu.umss.bank.presentation.BankUI;

public class Main {
    public static void main(String[] args) {
        AccountDao accountDao = AccountDaoImpl.getInstance();
        AccountService accountService = new AccountServiceImpl(accountDao);
        BankUI bankUI = new BankUI(accountService);

        bankUI.showMenu();
    }
}
