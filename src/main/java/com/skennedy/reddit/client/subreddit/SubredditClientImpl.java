package com.skennedy.reddit.client.subreddit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.adapters.DateLongTypeAdapter;
import com.skennedy.reddit.client.common.adapters.LanguageCodeAdapter;
import com.skennedy.reddit.client.common.model.LanguageCode;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.subreddit.request.SubredditRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.Date;

public class SubredditClientImpl implements SubredditClient {

    private final Access access;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public SubredditClientImpl(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongTypeAdapter())
                .registerTypeAdapter(LanguageCode.class, new LanguageCodeAdapter())
                .create();
    }

    @Override
    public SubredditRequest r(String subreddit) throws IllegalAccessException {
        return new SubredditRequest(access, httpClient, subreddit, OAuthScope.READ);
    }
}
