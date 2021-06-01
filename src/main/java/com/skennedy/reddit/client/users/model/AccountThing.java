package com.skennedy.reddit.client.users.model;

import com.skennedy.reddit.client.account.model.Account;
import com.skennedy.reddit.client.common.model.Thing;

public class AccountThing implements Thing<Account> {

    private String kind;
    private Account data;

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public Account getData() {
        return data;
    }
}
