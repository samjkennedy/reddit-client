package com.skennedy.reddit.client.account;

import com.skennedy.reddit.client.account.model.Account;
import com.skennedy.reddit.client.account.model.SubredditKarma;
import com.skennedy.reddit.client.common.response.Response;

import java.util.List;

public interface AccountClient {

    Response<Account> me() throws Exception;
    Response<List<SubredditKarma>> karma() throws Exception;

}
