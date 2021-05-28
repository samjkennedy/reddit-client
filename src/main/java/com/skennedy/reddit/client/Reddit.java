package com.skennedy.reddit.client;

import com.skennedy.reddit.client.account.AccountClient;
import com.skennedy.reddit.client.best.BestClientImpl;
import com.skennedy.reddit.client.search.SearchClient;
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
     * @return A SearchClient instance
     */
    SearchClient search();

    /**
     * Begins a flow for submitting posts, comments, and replies
     * @return A SubmissionClient instance
     */
    SubmissionClient submit();

    /**
     * Begins a flow for accessing details of a subreddit such as rules and the sidebar
     * @return A SubredditClient instance
     */
    SubredditClient subreddit();

    /**
     * Creates a request to get the front page
     * @return A BestRequest instance
     */
    BestClientImpl.BestRequest best();
}
