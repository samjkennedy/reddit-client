package com.skennedy.reddit.client.search;

import org.apache.http.impl.client.CloseableHttpClient;

public class SearchClientFactory {

    public static SearchClient getClient(String token, CloseableHttpClient httpClient) {
        return new SearchClientImpl(token, httpClient);
    }

    private SearchClientFactory() {
    }
}
