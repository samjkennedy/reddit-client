package com.skennedy.reddit.client.subreddit;

import com.skennedy.reddit.client.authorization.model.Access;
import org.apache.http.impl.client.CloseableHttpClient;

public class SubredditClientFactory {

    public static SubredditClient getClient(Access access, CloseableHttpClient httpClient) {
        return new SubredditClientImpl(access, httpClient);
    }

    private SubredditClientFactory() {
    }
}
