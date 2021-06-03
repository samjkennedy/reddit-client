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
import com.skennedy.reddit.client.subreddit.request.SubredditsRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.Arrays;
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

    @Override
    public SubredditRequest r(String subreddit) {
        return new SubredditRequest(access, httpClient, subreddit);
    }

    @Override
    public SubredditsRequest r(String subreddit, String... subreddits) {
        return new SubredditsRequest(access, httpClient, subreddit, subreddits);
    }

    @Override
    public SubredditRequest subreddit(Subreddit subreddit) {
        return new SubredditRequest(access, httpClient, subreddit);
    }

    @Override
    public SubredditsRequest subreddits(Subreddit subreddit, Subreddit... subreddits) {
        return new SubredditsRequest(access, httpClient, subreddit.getDisplayName(),
                Arrays.stream(subreddits)
                        .map(Subreddit::getDisplayName)
                        .toArray(String[]::new));
    }

    @Override
    public SubredditsRequest r(Collection<String> subreddits) {
        return new SubredditsRequest(access, httpClient, subreddits);
    }

    @Override
    public SubredditsRequest subreddits(Collection<Subreddit> subreddits) {
        return new SubredditsRequest(access, httpClient, subreddits.stream()
                .map(Subreddit::getDisplayName)
                .collect(Collectors.toList()));
    }

    @Override
    public MySubredditsRequest mine() {
        return new MySubredditsRequest(access, httpClient);
    }
}
