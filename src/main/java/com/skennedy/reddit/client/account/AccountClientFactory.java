package com.skennedy.reddit.client.account;

import com.skennedy.reddit.client.authorization.model.Access;
import org.apache.http.impl.client.CloseableHttpClient;

public class AccountClientFactory {

    public static AccountClient getClient(Access access, CloseableHttpClient httpClient) {
        return new AccountClientImpl(access, httpClient);
    }

    private AccountClientFactory() {

    }

}
