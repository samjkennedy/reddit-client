package com.skennedy.reddit.client.best;

import org.apache.http.impl.client.CloseableHttpClient;

public class BestClientFactory {

    public static BestClient getClient(String accessToken, CloseableHttpClient httpClient) {
        return new BestClientImpl(accessToken, httpClient);
    }

    private BestClientFactory() {

    }
}
