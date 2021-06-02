package com.skennedy.reddit.client.subreddit;

import com.skennedy.reddit.client.listing.model.Subreddit;
import com.skennedy.reddit.client.subreddit.request.MySubredditsRequest;
import com.skennedy.reddit.client.subreddit.request.SubredditRequest;

import java.util.Collection;

public interface SubredditClient {

    SubredditRequest r(String... subreddits);
    SubredditRequest r(Collection<Subreddit> subreddits);
    MySubredditsRequest mine();
}
