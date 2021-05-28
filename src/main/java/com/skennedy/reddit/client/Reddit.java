package com.skennedy.reddit.client;

import com.skennedy.reddit.client.account.AccountClient;
import com.skennedy.reddit.client.listing.ListingClient;
import com.skennedy.reddit.client.submit.SubmissionClient;
import com.skennedy.reddit.client.subreddit.SubredditClient;

public interface Reddit extends AutoCloseable {

    /**
     * Refreshes the Reddit client auth token
     * @return The refreshed Reddit client
     * @throws IllegalAccessException if the credentials cannot be refreshed
     */
    Reddit refresh() throws Exception;

    /**
     * Begins a flow for endpoints under the account section. E.g. /me, /karma
     * @return An AccountClient instance
     */
    AccountClient account();

    /**
     * Begins a flow for searching posts, comments, and subreddits
     * @return A ListingClient instance
     */
    ListingClient listing();

    /**
     * Begins a flow for submitting posts, comments, and replies
     * @return A SubmissionClient instance
     */
    SubmissionClient submit();

    /**
     * Begins a flow for accessing details of a subreddit such as rules and the sidebar
     * @return A SubredditClient instance
     */
    SubredditClient subreddits();
}
