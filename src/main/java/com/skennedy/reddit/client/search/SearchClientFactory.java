package com.skennedy.reddit.client.search;

import com.skennedy.reddit.client.authorization.model.Access;
import org.apache.http.impl.client.CloseableHttpClient;

public class SearchClientFactory {

    public static SearchClient getClient(Access access, CloseableHttpClient httpClient) {
        return new SearchClientImpl(access, httpClient);
    }

    private SearchClientFactory() {
    }
}
