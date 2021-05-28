package com.skennedy.reddit.client.subreddit;

import org.apache.http.impl.client.CloseableHttpClient;

public class SubredditClientFactory {

    public static SubredditClient getClient(String token, CloseableHttpClient httpClient) {
        return new SubredditClientImpl(token, httpClient);
    }

    private SubredditClientFactory() {
    }
}
