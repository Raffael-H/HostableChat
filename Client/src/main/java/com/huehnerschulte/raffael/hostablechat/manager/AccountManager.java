package com.huehnerschulte.raffael.hostablechat.manager;

import com.huehnerschulte.raffael.hostablechat.data.Account;

public class AccountManager {
    private static AccountManager instance;
    private Account account;

    private AccountManager() {
    }

    public static synchronized AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
