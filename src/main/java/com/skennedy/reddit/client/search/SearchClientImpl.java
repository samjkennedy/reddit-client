package com.skennedy.reddit.client.search;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.search.model.Submission;
import com.skennedy.reddit.client.search.request.CommentRequest;
import com.skennedy.reddit.client.search.request.SubmissionRequest;
import com.skennedy.reddit.client.search.request.SubredditSearchRequest;
import com.skennedy.reddit.client.subreddit.request.RisingRequest;
import org.apache.http.impl.client.CloseableHttpClient;

public class SearchClientImpl implements SearchClient {

    private final String token;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public SearchClientImpl(String token, CloseableHttpClient httpClient) {
        this.token = token;
        this.httpClient = httpClient;
        gson = new GsonBuilder().create();
    }

    @Override
    public SubmissionRequest r(String subreddit) {
        return new SubmissionRequest(httpClient, token, subreddit);
    }

    @Override
    public SubredditSearchRequest subreddits(String search) {
        return new SubredditSearchRequest(httpClient, token, search);
    }

    @Override
    public CommentRequest comments(Submission submission) {
        return new CommentRequest(httpClient, token, submission.getSubreddit(), submission.getId());
    }

    @Override
    public CommentRequest comments(String subreddit, String article) {
        return new CommentRequest(httpClient, token, subreddit, article);
    }

}
