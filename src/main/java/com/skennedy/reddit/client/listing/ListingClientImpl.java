package com.skennedy.reddit.client.listing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.listing.request.BestRequest;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.listing.request.CommentRequest;
import com.skennedy.reddit.client.listing.request.SubmissionRequest;
import com.skennedy.reddit.client.listing.request.SubredditSearchRequest;
import org.apache.http.impl.client.CloseableHttpClient;

public class ListingClientImpl implements ListingClient {

    private final Access access;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public ListingClientImpl(Access access, CloseableHttpClient httpClient) {
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

    @Override
    public BestRequest best() throws IllegalAccessException {
        return new BestRequest(access, httpClient);
    }

}
