package com.skennedy.reddit.client.listing;

import com.skennedy.reddit.client.authorization.model.Access;
import org.apache.http.impl.client.CloseableHttpClient;

public class SearchClientFactory {

    public static ListingClient getClient(Access access, CloseableHttpClient httpClient) {
        return new ListingClientImpl(access, httpClient);
    }

    private SearchClientFactory() {
    }
}
