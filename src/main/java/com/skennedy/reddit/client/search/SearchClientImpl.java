package com.skennedy.reddit.client.search;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.search.model.Submission;
import com.skennedy.reddit.client.search.request.CommentRequest;
import com.skennedy.reddit.client.search.request.SubmissionRequest;
import com.skennedy.reddit.client.search.request.SubredditSearchRequest;
import org.apache.http.impl.client.CloseableHttpClient;

public class SearchClientImpl implements SearchClient {

    private final Access access;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public SearchClientImpl(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
        gson = new GsonBuilder().create();
    }

    @Override
    public SubmissionRequest r(String subreddit) throws IllegalAccessException {
        return new SubmissionRequest(access, httpClient, subreddit);
    }

    @Override
    public SubredditSearchRequest subreddits(String search) throws IllegalAccessException {
        return new SubredditSearchRequest(access, httpClient, search);
    }

    @Override
    public CommentRequest comments(Submission submission) throws IllegalAccessException {
        return new CommentRequest(access, httpClient, submission.getSubreddit(), submission.getId());
    }

    @Override
    public CommentRequest comments(String subreddit, String article) throws IllegalAccessException {
        return new CommentRequest(access, httpClient, subreddit, article);
    }

}
