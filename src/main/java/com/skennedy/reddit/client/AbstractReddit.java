package com.skennedy.reddit.client;

import com.skennedy.reddit.client.account.AccountClient;
import com.skennedy.reddit.client.account.AccountClientFactory;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.listing.ListingClient;
import com.skennedy.reddit.client.listing.SearchClientFactory;
import com.skennedy.reddit.client.submit.SubmissionClient;
import com.skennedy.reddit.client.submit.SubmissionClientFactory;
import com.skennedy.reddit.client.subreddit.SubredditClient;
import com.skennedy.reddit.client.subreddit.SubredditClientFactory;
import com.skennedy.reddit.client.users.UserClient;
import com.skennedy.reddit.client.users.UserClientImpl;
import org.apache.http.impl.client.CloseableHttpClient;

public abstract class AbstractReddit implements Reddit {

    protected Access access;
    protected CloseableHttpClient httpClient;

    private AccountClient accountClient;
    private ListingClient listingClient;
    private SubmissionClient submissionClient;
    private SubredditClient subredditClient;
    private UserClient userClient;

    public AbstractReddit(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
    }

    @Override
    public AccountClient account() {
        if (accountClient == null) {
            accountClient = AccountClientFactory.getClient(access, httpClient);
        }
        return accountClient;
    }

    @Override
    public ListingClient listing() {
        if (listingClient == null) {
            listingClient = SearchClientFactory.getClient(access, httpClient);
        }
        return listingClient;
    }

    @Override
    public SubmissionClient submit() {
        if (submissionClient == null) {
            submissionClient = SubmissionClientFactory.getClient(access, httpClient);
        }
        return submissionClient;
    }

    @Override
    public SubredditClient subreddits() {
        if (subredditClient == null) {
            subredditClient = SubredditClientFactory.getClient(access, httpClient);
        }
        return subredditClient;
    }

    @Override
    public UserClient users() {
        if (userClient == null) {
            userClient = new UserClientImpl(access, httpClient);
        }
        return userClient;
    }

    protected void refreshClients(Access access) {
        if (accountClient == null) {
            accountClient = AccountClientFactory.getClient(access, httpClient);
        }
        if (listingClient == null) {
            listingClient = SearchClientFactory.getClient(access, httpClient);
        }
        if (submissionClient == null) {
            submissionClient = SubmissionClientFactory.getClient(access, httpClient);
        }
        if (subredditClient == null) {
            subredditClient = SubredditClientFactory.getClient(access, httpClient);
        }
        if (userClient == null) {
            userClient = new UserClientImpl(access, httpClient);
        }
    }
}
