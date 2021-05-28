package com.skennedy.reddit.client;

import com.skennedy.reddit.client.account.AccountClient;
import com.skennedy.reddit.client.account.AccountClientFactory;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.best.BestClient;
import com.skennedy.reddit.client.best.BestClientFactory;
import com.skennedy.reddit.client.best.request.BestRequest;
import com.skennedy.reddit.client.search.SearchClient;
import com.skennedy.reddit.client.search.SearchClientFactory;
import com.skennedy.reddit.client.submit.SubmissionClient;
import com.skennedy.reddit.client.submit.SubmissionClientFactory;
import com.skennedy.reddit.client.subreddit.SubredditClient;
import com.skennedy.reddit.client.subreddit.SubredditClientFactory;
import org.apache.http.impl.client.CloseableHttpClient;

public abstract class AbstractReddit implements Reddit {

    protected Access access;
    protected CloseableHttpClient httpClient;

    private AccountClient accountClient;
    private SearchClient searchClient;
    private SubmissionClient submissionClient;
    private SubredditClient subredditClient;
    private BestClient bestClient;

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
    public SearchClient search() {
        if (searchClient == null) {
            searchClient = SearchClientFactory.getClient(access, httpClient);
        }
        return searchClient;
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
    public BestRequest best() throws IllegalAccessException {
        if (bestClient == null) {
            bestClient = BestClientFactory.getClient(access, httpClient);
        }
        return bestClient.best();
    }

    protected void refreshClients(Access access) {
        if (accountClient == null) {
            accountClient = AccountClientFactory.getClient(access, httpClient);
        }
        if (searchClient == null) {
            searchClient = SearchClientFactory.getClient(access, httpClient);
        }
        if (submissionClient == null) {
            submissionClient = SubmissionClientFactory.getClient(access, httpClient);
        }
        if (subredditClient == null) {
            subredditClient = SubredditClientFactory.getClient(access, httpClient);
        }
        if (bestClient == null) {
            bestClient = BestClientFactory.getClient(access, httpClient);
        }
    }
}
