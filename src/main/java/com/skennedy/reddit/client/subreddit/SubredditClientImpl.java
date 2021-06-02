package com.skennedy.reddit.client.subreddit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.adapters.DateLongTypeAdapter;
import com.skennedy.reddit.client.common.adapters.LanguageCodeAdapter;
import com.skennedy.reddit.client.common.model.LanguageCode;
import com.skennedy.reddit.client.listing.model.Subreddit;
import com.skennedy.reddit.client.subreddit.request.MySubredditsRequest;
import com.skennedy.reddit.client.subreddit.request.SubredditRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

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

    /**
     * Begins a flow for accessing one or more subreddits. Some functions can only be performed on one subreddit, e.g. about()
     * @param subreddits at least one subreddit
     * @return a SubredditRequest instance
     */
    @Override
    public SubredditRequest r(String... subreddits) {
        return new SubredditRequest(access, httpClient, subreddits);
    }

    /**
     * Begins a flow for accessing one or more subreddits. Some functions can only be performed on one subreddit, e.g. about()
     * @param subreddits at least one subreddit
     * @return a SubredditRequest instance
     */
    @Override
    public SubredditRequest r(Collection<Subreddit> subreddits) {
        return new SubredditRequest(access, httpClient, subreddits.stream().map(Subreddit::getDisplayName).collect(Collectors.toList()));
    }

    /**
     * Begins a flow for accessing the authorised user's subscribed subreddits
     * @return a MySubredditsRequest instance
     */
    @Override
    public MySubredditsRequest mine() {
        return new MySubredditsRequest(access, httpClient);
    }
}
