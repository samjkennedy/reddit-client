package com.skennedy.reddit.client.account;

import org.apache.http.impl.client.CloseableHttpClient;

public class AccountClientFactory {

    public static AccountClient getClient(String token, CloseableHttpClient httpClient) {
        return new AccountClientImpl(token, httpClient);
    }

    private AccountClientFactory() {

    }

}
