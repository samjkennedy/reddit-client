package com.skennedy.reddit.client.listing;

import com.skennedy.reddit.client.listing.request.BestRequest;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.listing.request.CommentRequest;
import com.skennedy.reddit.client.listing.request.SubmissionRequest;
import com.skennedy.reddit.client.listing.request.SubredditSearchRequest;

public interface ListingClient {

    /**
     * Begins a flow for searching submissions from a given subreddit.
     *
     * @param subreddit The non-prefixed name of the subreddit, i.e. "java", not "r/java"
     * @return a SubmissionRequest instance over the given subreddit
     */
    SubmissionRequest r(String subreddit) throws IllegalAccessException;

    /**
     * Begins a flow for searching for subreddits with a given search.
     *
     * @param search The search string
     * @return a SubredditSearchRequest instance over the given search query
     */
    SubredditSearchRequest subreddits(String search) throws IllegalAccessException;


    /**
     * Begins a flow for searching for comments on a given submission.
     *
     * @param submission The submission to search for comments on
     * @return a CommentRequest over the given submission
     */
    CommentRequest comments(Submission submission) throws IllegalAccessException;

    /**
     * Begins a flow for searching for comments on a given article and subreddit.
     *
     * @param subreddit The non-prefixed name of the subreddit, i.e. "java", not "r/java"
     * @param article The id of a submission to get comments for
     * @return a CommentRequest instance over the given article in the given subreddit
     * @see Submission#getId() to get the article id
     */
    CommentRequest comments(String subreddit, String article) throws IllegalAccessException;

    BestRequest best() throws IllegalAccessException;
}
